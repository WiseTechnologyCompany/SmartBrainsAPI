INSERT INTO situacao_cadastro (descricao) VALUES
    ('ATIVO'),
    ('EXCLUIDO');

INSERT INTO estado_civil (descricao) VALUES
    ('SOLTEIRO'),
    ('UNIAO ESTAVEL'),
    ('CASADO'),
    ('DIVORCIADO'),
    ('VIUVO');

INSERT INTO tipo_movimentacao (descricao) VALUES
     ('ENTRADA'),
     ('GASTOS FIXOS'),
     ('DESPESAS');

INSERT INTO tipo_categoria (descricao) VALUES
    ('SALARIO'),
    ('ALIMENTACAO'),
    ('TRANSPORTE'),
    ('MORADIA'),
    ('LAZER'),
    ('SAUDE'),
    ('EDUCACAO'),
    ('COMPRAS'),
    ('CONTAS'),
    ('OUTROS');

INSERT INTO usuarios (nome, sobrenome, email, cpf, profissao, empresa, data_nascimento, telefone, id_estadocivil, id_situacaocadastro) VALUES
     ('Carlos', 'Silva', 'carlos@email.com', '123.456.789-00', 'Engenheiro', 'TechCorp', '1990-05-15', '(11) 91234-5678',  1,1 ),
     ('Teste', 'Test', 'teste@teste.com', '987.654.321-00', 'Médico', 'Hospital Vida', '1985-09-10', '(21) 99876-5432',  2, 1),
     ('Alex', 'Souza', 'alex@email.com', '456.123.789-99', 'Designer', 'Creative Studio', '1995-02-20', '(31) 97654-3210',  3, 1);

INSERT INTO movimentacao (id_usuario, id_tipomovimentacao, id_tipocategoria, descricao, observacao, valor) VALUES
     (1, 2, 4, 'Pagamento de aluguel', 'Paguei meu aluguel', 1200.00),
     (2, 1, 1, 'Recebimento de bônus', 'Bõnus do Serviço', 2500.00),
     (3, 2, 7,'Transferência para conta poupança', 'Transferência', 300.00);

INSERT INTO autenticacao (email, password) VALUES
    ('teste@teste.com.br', '$2a$10$2rVGvA/cxINIVYrg3Xa/BuwhKci.u/AKfGerva8hiUfa.tcPtqTke');