CREATE SEQUENCE user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE users_configs ALTER COLUMN id SET DEFAULT nextval('user_seq'::regclass);

insert INTO users_configs
values (nextval('user_seq'), 'roberto', 'alonso', 'rb_alonso@gmail.com', 'secret_password');