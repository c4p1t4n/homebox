import json
import boto3

def lambda_handler(event, context):
    boto3Session = boto3.Session()

    credentials = {
        'AccessKeyId': boto3Session.get_credentials().access_key,
        'SecretAccessKey': boto3Session.get_credentials().secret_key,
        'Token': boto3Session.get_credentials().token
    }

    return {
        'statusCode': 200,
        'body': json.dumps(credentials)
    }


# Layer necessário: arn:aws:lambda:us-east-1:336392948345:layer:AWSSDKPandas-Python38:1


if __name__ == '__main__':
    print(lambda_handler({}, {}))