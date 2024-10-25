INSERT INTO users(
    username, password, enabled)
VALUES ('admin', '{noop}admin', 1);

INSERT INTO authorities(
    username, authority)
VALUES ('admin', 'ROLE_ADMIN');