DROP DATABASE scjdt

CREATE DATABASE scjdt

CREATE TABLE "categoria_funcao" (
"id_categoria" SERIAL PRIMARY KEY,
"nome_categoria" text NOT NULL,
"created_at" timestamp NOT NULL,
"updated_at" timestamp
);

CREATE TABLE "funcao" (
"id_funcao" SERIAL PRIMARY KEY,
"nome_funcao" text NOT NULL,
"salario_funcao" numeric (8,2),
"descricao_funcao" text,
"id_categoria" int NOT NULL,
"created_at" timestamp NOT NULL,
"updated_at" timestamp
);

ALTER TABLE "funcao" ADD FOREIGN KEY ("id_categoria") REFERENCES "categoria_funcao" ("id_categoria");

CREATE TABLE "local_trabalho" (
"id_local_trabalho" SERIAL PRIMARY KEY,
"nome_local_trabalho" text NOT NULL UNIQUE,
"created_at" timestamp NOT NULL,
"updated_at" timestamp
);

CREATE TABLE "programacao_horaria" (
  "id_programacao" SERIAL PRIMARY KEY,
  "nome_programacao" text NOT NULL,
  "inicio_horario" time NOT NULL,
  "fim_horario" time NOT NULL,
  "descricao" text NOT NULL,
  "id_local_inicio" int NOT NULL,
  "status_programacao" int NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp
);

ALTER TABLE "programacao_horaria" ADD FOREIGN KEY ("id_local_inicio") REFERENCES "local_trabalho" ("id_local_trabalho");


CREATE TABLE "usuario" (
"id_usuario" SERIAL PRIMARY KEY,
"nome_usuario" text NOT NULL UNIQUE,
"senha" text NOT NULL,
"created_at" timestamp NOT NULL,
"updated_at" timestamp
);

CREATE TABLE "colaborador" (
"id_colaborador" SERIAL PRIMARY KEY,
"nome_colaborador" text NOT NULL,
"data_nascimento" timestamp NOT NULL,
"rg" text NOT NULL,
"cpf" text NOT NULL,
"email" text,
"carga_horaria" time,
"tipo_moradia" text,
"cep" text,
"rua" text,
"numero" text,
"complemento" text,
"bairro" text,
"cidade" text,
"estado" text,
"id_funcao" int,
"ativo" int,
"created_at" timestamp,
"updated_at" timestamp
);

ALTER TABLE "colaborador" ADD FOREIGN KEY ("id_funcao") REFERENCES "funcao" ("id_funcao");



-- create procedure
CREATE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
NEW.updated_at = NOW();
RETURN NEW;
END;
$$ LANGUAGE plpgsql

-- auto updated_at user
CREATE TRIGGER set_timestamp 
BEFORE UPDATE ON usuario
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

-- auto updated_at localTrabalho
CREATE TRIGGER set_timestamp_localTrabalho 
BEFORE UPDATE ON local_trabalho
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

-- auto updated_at colaborador
CREATE TRIGGER set_timestamp_localTrabalho 
BEFORE UPDATE ON colaborador
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

-- auto updated_at categoria_funcao
CREATE TRIGGER set_timestamp_localTrabalho 
BEFORE UPDATE ON categoria_funcao
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

-- auto updated_at categoria_funcao
CREATE TRIGGER set_timestamp_localTrabalho 
BEFORE UPDATE ON programacao_horaria
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

