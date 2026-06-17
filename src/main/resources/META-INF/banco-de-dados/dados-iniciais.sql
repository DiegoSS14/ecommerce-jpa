insert into product (id, name, price, creation_date, description) values (1, 'Kindle', 499.0, date_sub(sysdate(), interval 1 day) 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into product (id, name, price, creation_date, description) values (2, 'Kindle 2', 499.0, date_sub(sysdate(), interval 1 day) 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into product (id, name, price, creation_date, description) values (3, 'Notebook', 5689.0, date_sub(sysdate(), interval 1 day) 'Conheça o novo notebook gamer com RTX 5090 e 64gb de ram DDR5.');

insert into client (id, name) values (1, 'Anderson Silva');
insert into client (id, name) values (2, 'Maurício Oliveira');
insert into client (id, name) values (3, 'Thiago Sousa');
insert into client (id, name) values (20, 'Diego Sousa');

insert into category (id, name) values (1, 'Eletronicos');

INSERT INTO invoice (id, invoice_number, issue_date) 
VALUES (100, 'NF-2026-0001', '2026-06-15');

INSERT INTO invoice (id, invoice_number, issue_date) 
VALUES (100, 'NF-2026-0001', '2026-06-15');

-- 2. INSERT na tabela principal (ordered)
INSERT INTO ordered (
    id,
    order_date, 
    creation_date, 
    last_update_date, 
    invoice_id, 
    client_id, 
    status, 
    total, 
    -- Colunas do @Embedded (Ajuste conforme os atributos reais da sua classe DeliveryAddressOrdered)
    address_street, 
    address_number, 
    address_city, 
    address_state, 
    address_zip_code
) VALUES (
    1,
    '2026-06-15',          -- order_date (gerado no @PrePersist)
    '2026-06-15',          -- creation_date (gerado no @PrePersist)
    NULL,                  -- last_update_date (inicia nulo, atualiza no @PostUpdate)
    100,                   -- invoice_id (relacionamento @OneToOne)
    20,                     -- client_id (relacionamento @ManyToOne)
    'PAID',                -- status (salvo como String devido ao EnumType.STRING)
    159.90,                -- total (BigDecimal calculado com base nos itens)
    -- Valores do @Embedded
    'Av. Paulista',        -- address_street
    '1000',                -- address_number
    'São Paulo',           -- address_city
    'SP',                  -- address_state
    '01310-100'            -- address_zip_code
);