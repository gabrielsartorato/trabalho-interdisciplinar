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
);


