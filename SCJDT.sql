CREATE TABLE "categoriaFuncao" (
  "idCategoria" SERIAL PRIMARY KEY,
  "nomeCategoria" text NOT NULL,
  "salarioCategoria" int,
  "descricaoCategoria" text
);

CREATE TABLE "localTrabalho" (
  "idLocalTrabalho" SERIAL PRIMARY KEY,
  "nomeLocalTrabalho" text NOT NULL
);

CREATE TABLE "usuario" (
  "idUsuario" SERIAL PRIMARY KEY,
  "nomeUsuario" text NOT NULL,
  "senha" text NOT NULL
  "created_at" timestamp NOT NULL
  "updated_at" timestamp
);

-- create procedure
CREATE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql

-- auto updated_at products
CREATE TRIGGER set_timestamp 
BEFORE UPDATE ON usuario
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();


