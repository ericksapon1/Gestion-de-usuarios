CREATE USER 'nombre_usuario'@'localhost' IDENTIFIED BY 'contraseña';
GRANT ALL PRIVILEGES ON gestion_usuarios.* TO 'nombre_usuario'@'localhost';
FLUSH PRIVILEGES;
