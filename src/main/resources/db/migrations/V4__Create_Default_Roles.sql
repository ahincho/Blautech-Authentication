-- V4 Create Default Roles
INSERT INTO roles (name)
VALUES
    ('Administrator'),
    ('Customer')
ON CONFLICT (name) DO NOTHING;