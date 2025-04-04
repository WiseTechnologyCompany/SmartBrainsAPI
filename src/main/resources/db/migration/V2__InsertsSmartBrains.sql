INSERT INTO situacao_cadastro (descricao) VALUES
    ('ATIVO'),
    ('EXCLUIDO');

INSERT INTO generos (descricao) VALUES
    ('MASCULINO'),
    ('FEMININO'),
    ('OUTROS');

INSERT INTO estado_civil (descricao) VALUES
    ('SOLTEIRO'),
    ('UNIAO ESTAVEL'),
    ('CASADO'),
    ('DIVORCIADO'),
    ('VIUVO');

INSERT INTO tipo_movimentacao (descricao) VALUES
     ('ENTRADA'),
     ('SAIDA');

INSERT INTO usuarios (nome, sobrenome, email, senha, cpf, profissao, empresa, data_nascimento, telefone, id_estadocivil, id_situacaocadastro) VALUES
     ('Carlos', 'Silva', 'carlos@email.com', 'senha123', '123.456.789-00', 'Engenheiro', 'TechCorp', '1990-05-15', '(11) 91234-5678',  1,1 ),
     ('Mariana', 'Oliveira', 'mariana@email.com', 'senha456', '987.654.321-00', 'Médica', 'Hospital Vida', '1985-09-10', '(21) 99876-5432',  2, 1),
     ('Alex', 'Souza', 'alex@email.com', 'senha789', '456.123.789-99', 'Designer', 'Creative Studio', '1995-02-20', '(31) 97654-3210',  3, 1);

INSERT INTO cofre (id_usuario, id_tipomovimentacao, valor, observacao) VALUES
     (1, 1, 1000.00, 'Depósito inicial'),
     (2, 1, 5000.50, 'Salário recebido'),
     (3, 2, 200.75, 'Pagamento de conta');

INSERT INTO movimentacao (id_usuario, id_tipomovimentacao, descricao, valor) VALUES
     (1, 2, 'Pagamento de aluguel', 1200.00),
     (2, 1, 'Recebimento de bônus', 2500.00),
     (3, 2, 'Transferência para conta poupança', 300.00);

INSERT INTO autenticacao (email, password) VALUES
    ('teste@teste.com.br', '$2a$10$2rVGvA/cxINIVYrg3Xa/BuwhKci.u/AKfGerva8hiUfa.tcPtqTke');