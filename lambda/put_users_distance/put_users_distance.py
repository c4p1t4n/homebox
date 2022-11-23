import json
import pymysql
import requests
import pandas as pd
import awswrangler as wr

def lambda_handler(event, context):
    # body = json.loads(event['body'])

    # db_connection = pymysql.connect(
    #     user='homebox',
    #     host='localhost',
    #     database='homebox',
    #     password='Homebox265@',
    # )

    db_connection = pymysql.connect(
        host='54.198.76.123',
        user='homebox',
        database='homebox',
        password='Homebox265@',
    )

    df = pd.read_sql(sql=f"select id_user, name, TYPE as type, cep, rating from users_rating where id_user = {json.loads(event['body'])['id']} or type = 'worker'", con=db_connection)

    customer = df[df['id_user'] == json.loads(event['body'])['id']].to_dict('records')[0]
    df_worker = df[df['type'] == 'worker']

    df_final = pd.DataFrame(data=[], columns=['id_customer', 'customer_name', 'customer_cep', 'id_worker', 'worker_name', 'worker_cep', 'worker_rating', 'distance'])

    df_worker['customer_cep'] = customer['cep']
    df_worker['customer_name'] = customer['name']
    df_worker['id_customer'] = customer['id_user']
    df_worker['worker_cep'] = df_worker['cep']
    df_worker['worker_name'] = df_worker['name']
    df_worker['id_worker'] = df_worker['id_user']
    df_worker['worker_rating'] = df_worker['rating']

    df_worker = df_worker[['id_customer', 'customer_name', 'customer_cep', 'id_worker', 'worker_name', 'worker_cep', 'worker_rating']]

    url = 'https://qu56ty27df2dwnnx6k4ktjrkmm0senfu.lambda-url.us-east-1.on.aws'

    for _, row in df_worker.iterrows():

        # url = f'https://distancep.herokuapp.com/distance/{row["worker_cep"]}/{row["customer_cep"]}'

        print(f'{url = }')

        try:
            # distance = requests.get(url).json()
            distance = requests.post(url=url, data=json.dumps({'cep1': row['worker_cep'], 'cep2': row['customer_cep']}), headers={"Content-Type": "application/json", "Accept": "application/json"}).json()

            print(f'{distance = }')

            distance = distance['distance']

            row['distance'] = distance

            print(row)

            df_final = df_final.append(row)

        except Exception as e:
            print(f'Erro ao calcular a distancia \n{e = }')
            continue

    df_final['knn_distance'] = df_final['distance'] - 3.72 * df_final['worker_rating'].fillna(0)

    df_final = df_final.sort_values(by='knn_distance', ascending=True)

    wr.s3.to_parquet(
        df=df_final,
        dataset=True,
        mode='append',
        path='s3://distancia/'
    )


    return {
        'statusCode': 200
    }


# Layer necessário: arn:aws:lambda:us-east-1:336392948345:layer:AWSSDKPandas-Python38:1


if __name__ == '__main__':
    lambda_handler({'body': '{"id": 4}'}, {})