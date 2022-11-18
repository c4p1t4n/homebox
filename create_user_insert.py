from faker import Faker
import random
import gerador_endereco


faker = Faker(locale='pt-br')


ceps = gerador_endereco.get_list_ceps_bairros(municipio='são paulo', loops=10)[0][:100]
insert_sql = f'INSERT INTO user(name, gender, email, password, cpf, type, birth_date, cep, authenticated) VALUES\n'
for cep in ceps:
    cep = cep.replace('-', '')
    gender = random.choice(['Masculino', 'Feminino'])
    name = faker.first_name_male() if gender=="Masculino" else faker.first_name_female()
    type = random.choice(['worker', 'customer'])
    email = f'{name.split(" ")[0].lower()}{type}{random.randint(0, 100000)}@gmail.com'
    password = 'ExSenha1'
    cpf = faker.cpf().replace(".", "").replace("-", "")
    birth_date = faker.date()
    authenticated = 'n'
    insert_sql += f"('{name}', '{gender}', '{email}', SHA2('{password}', 256), '{cpf}', '{type}', '{birth_date}', '{cep}', '{authenticated}'),\n"

insert_sql = f'{insert_sql[:-2]};'

with open('./users_insert.sql', 'a') as f:
    f.write(insert_sql)