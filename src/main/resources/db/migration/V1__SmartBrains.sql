CREATE TABLE IF NOT EXISTS generos (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS estado_civil (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS situacao_cadastro (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tipo_movimentacao (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_nascimento DATE NOT NULL,
    profissao VARCHAR(100) NOT NULL,
    empresa VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    id_estadocivil INT NOT NULL,
    id_situacaocadastro INT DEFAULT 1,
    data_cadastro DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (id_estadocivil) REFERENCES estado_civil(id),
    FOREIGN KEY (id_situacaocadastro) REFERENCES situacao_cadastro(id)
);

CREATE TABLE IF NOT EXISTS cofre (
    id SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_tipomovimentacao INT NOT NULL,
    valor DECIMAL(11,2) NOT NULL,
    observacao TEXT,
    data_criacao DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_tipomovimentacao) REFERENCES tipo_movimentacao(id)
);

CREATE TABLE IF NOT EXISTS movimentacao (
    id SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_tipomovimentacao INT NOT NULL,
    descricao TEXT,
    valor DECIMAL(11,2) NOT NULL,
    data_criacao DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_tipomovimentacao) REFERENCES tipo_movimentacao(id)
);

CREATE TABLE IF NOT EXISTS autenticacao(
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password TEXT NOT NULL
);