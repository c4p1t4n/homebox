import json
import requests

def lambda_handler(event, context):
    body = json.loads(event['body'])

    return {
        'statusCode': 200,
        'body': json.dumps(requests.get(f'https://distancep.herokuapp.com/distance/{body["cep1"]}/{body["cep2"]}').json())
    }


# Layer necessário: arn:aws:lambda:us-east-1:336392948345:layer:AWSSDKPandas-Python38:1
