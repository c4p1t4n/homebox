import json
import awswrangler as wr
import pandas as pd

def lambda_handler(event, context):
    body = json.loads(event['body'])

    user_id = body['user_id']
    qtd_workers = body['qtd']

    df = wr.s3.read_parquet(
        path='s3://distancia/',
        dataset=True,
    )

    df = df[df['id_customer'] == user_id].drop_duplicates(['id_customer', 'id_worker'], keep='last')[df.columns][:qtd_workers]

    print(df)

    dists = df.to_dict('records')

    return {
        'statusCode': 200,
        'body': json.dumps(dists)
    }


# Layer necessário: arn:aws:lambda:us-east-1:336392948345:layer:AWSSDKPandas-Python38:1


if __name__ == '__main__':
    print(lambda_handler({'body': '{"user_id": 7, "qtd": 3}'}, {}))