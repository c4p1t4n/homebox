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

    df = pd.read_sql(sql='select id_user, name, type, cep from user', con=db_connection)

    df_worker = df[df['type'] == 'worker']
    df_customer = df[df['type'] == 'customer']

    # print(df_worker.head())
    # print(df_customer.head())

    df_final = pd.DataFrame(data=[], columns=['id_customer', 'customer_name', 'customer_cep', 'id_worker', 'worker_name', 'worker_cep', 'distance'])

    for _, customer in df_customer.iterrows():
        df_customer_1 = df_worker.copy(deep=True)

        df_customer_1['customer_cep'] = customer['cep']
        df_customer_1['customer_name'] = customer['name']
        df_customer_1['id_customer'] = customer['id_user']
        df_customer_1['worker_cep'] = df_customer_1['cep']
        df_customer_1['worker_name'] = df_customer_1['name']
        df_customer_1['id_worker'] = df_customer_1['id_user']

        df_customer_1 = df_customer_1[['id_customer', 'customer_name', 'customer_cep', 'id_worker', 'worker_name', 'worker_cep']]

        for _, row in df_customer_1.iterrows():

            url = f'https://distancep.herokuapp.com/distance/{row["worker_cep"]}/{row["customer_cep"]}'

            print(f'{url = }')

            distance = requests.get(url).json()

            print(f'{distance = }')

            distance = distance['distance']

            row['distance'] = distance

            print(row)

            df_final = df_final.append(row)

    wr.s3.to_parquet(
        df=df,
        path=f's3://distancia/',
        dataset=True,
        mode='append'
    )


    return {
        'statusCode': 200
    }


# Layer necessário: arn:aws:lambda:us-east-1:336392948345:layer:AWSSDKPandas-Python38:1


if __name__ == '__main__':
    lambda_handler({}, {})