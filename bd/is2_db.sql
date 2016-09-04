-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.8.2
-- PostgreSQL version: 9.5
-- Project Site: pgmodeler.com.br
-- Model Author: ---


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: is2_db | type: DATABASE --
-- -- DROP DATABASE IF EXISTS is2_db;
-- CREATE DATABASE is2_db
-- 	OWNER = postgres
-- ;
-- -- ddl-end --
-- 

-- object: public.empleados | type: TABLE --
-- DROP TABLE IF EXISTS public.empleados CASCADE;
CREATE TABLE public.empleados(
	id serial NOT NULL,
	nombre varchar(50) NOT NULL,
	apellido varchar(50) NOT NULL,
	telefono varchar(20),
	email varchar(255),
	direccion varchar(120),
	id_localidad integer NOT NULL,
	CONSTRAINT pk_empleados PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.empleados OWNER TO postgres;
-- ddl-end --

-- object: public.localidades | type: TABLE --
-- DROP TABLE IF EXISTS public.localidades CASCADE;
CREATE TABLE public.localidades(
	id serial NOT NULL,
	nombre varchar(50) NOT NULL,
	CONSTRAINT pk_localidad PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.localidades OWNER TO postgres;
-- ddl-end --

-- object: public.productos | type: TABLE --
-- DROP TABLE IF EXISTS public.productos CASCADE;
CREATE TABLE public.productos(
	id serial NOT NULL,
	nombre varchar(50) NOT NULL,
	descripcion varchar(120) NOT NULL,
	precio integer NOT NULL,
	costo integer NOT NULL,
	stock_acual integer NOT NULL,
	stock_minimo integer NOT NULL,
	CONSTRAINT pk_productos PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.productos OWNER TO postgres;
-- ddl-end --

-- object: public.clientes | type: TABLE --
-- DROP TABLE IF EXISTS public.clientes CASCADE;
CREATE TABLE public.clientes(
	id serial NOT NULL,
	nombre varchar(50),
	apellido varchar(50),
	cedula varchar(10),
	ruc varchar(20),
	tipo varchar NOT NULL,
	telefono varchar(20) NOT NULL,
	direccion varchar(120) NOT NULL,
	email varchar(255),
	coor_lat varchar(50),
	coor_long varchar(50),
	id_localidad integer NOT NULL,
	CONSTRAINT pk_clientes PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.clientes OWNER TO postgres;
-- ddl-end --

-- object: public.proveedores | type: TABLE --
-- DROP TABLE IF EXISTS public.proveedores CASCADE;
CREATE TABLE public.proveedores(
	id serial NOT NULL,
	nombre varchar(50),
	apellido varchar(50),
	cedula varchar(10),
	ruc varchar(20),
	tipo varchar NOT NULL,
	telefono varchar(20) NOT NULL,
	direccion varchar(120) NOT NULL,
	email varchar(255),
	id_localidad integer NOT NULL,
	CONSTRAINT pk_proveedores PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.proveedores OWNER TO postgres;
-- ddl-end --

-- object: public.compras | type: TABLE --
-- DROP TABLE IF EXISTS public.compras CASCADE;
CREATE TABLE public.compras(
	id serial NOT NULL,
	monto_total integer NOT NULL,
	fecha date NOT NULL,
	id_proveedor integer NOT NULL,
	CONSTRAINT pk_compras PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.compras OWNER TO postgres;
-- ddl-end --

-- object: public.detalles_compras | type: TABLE --
-- DROP TABLE IF EXISTS public.detalles_compras CASCADE;
CREATE TABLE public.detalles_compras(
	id_compra integer NOT NULL,
	id_producto integer NOT NULL,
	cantidad integer NOT NULL,
	precio integer NOT NULL,
	CONSTRAINT pk_detalles_compras PRIMARY KEY (id_compra,id_producto)

);
-- ddl-end --
ALTER TABLE public.detalles_compras OWNER TO postgres;
-- ddl-end --

-- object: public.ventas | type: TABLE --
-- DROP TABLE IF EXISTS public.ventas CASCADE;
CREATE TABLE public.ventas(
	id serial NOT NULL,
	monto_total integer NOT NULL,
	fecha date NOT NULL,
	id_empleado integer NOT NULL,
	id_cliente integer NOT NULL,
	CONSTRAINT pk_ventas PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.ventas OWNER TO postgres;
-- ddl-end --

-- object: public.detalles_ventas | type: TABLE --
-- DROP TABLE IF EXISTS public.detalles_ventas CASCADE;
CREATE TABLE public.detalles_ventas(
	id_ventas integer NOT NULL,
	id_producto integer NOT NULL,
	cantidad integer NOT NULL,
	precio integer NOT NULL,
	CONSTRAINT pk_detalles_ventas PRIMARY KEY (id_ventas,id_producto)

);
-- ddl-end --
ALTER TABLE public.detalles_ventas OWNER TO postgres;
-- ddl-end --

-- object: public.pedidos | type: TABLE --
-- DROP TABLE IF EXISTS public.pedidos CASCADE;
CREATE TABLE public.pedidos(
	id serial NOT NULL,
	monto_total integer NOT NULL,
	fecha date NOT NULL,
	aprobado bool NOT NULL,
	id_empleado integer NOT NULL,
	id_cliente integer NOT NULL,
	CONSTRAINT pk_pedidos PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.pedidos OWNER TO postgres;
-- ddl-end --

-- object: public.detalles_pedidos | type: TABLE --
-- DROP TABLE IF EXISTS public.detalles_pedidos CASCADE;
CREATE TABLE public.detalles_pedidos(
	id_pedido integer NOT NULL,
	id_producto integer NOT NULL,
	cantidad integer NOT NULL,
	precio integer NOT NULL,
	CONSTRAINT pk_detalles_pedidos PRIMARY KEY (id_pedido,id_producto)

);
-- ddl-end --
ALTER TABLE public.detalles_pedidos OWNER TO postgres;
-- ddl-end --

-- object: public.usuarios | type: TABLE --
-- DROP TABLE IF EXISTS public.usuarios CASCADE;
CREATE TABLE public.usuarios(
	id serial NOT NULL,
	nombre varchar(20) NOT NULL,
	password varchar(100) NOT NULL,
	id_empleado integer,
	CONSTRAINT pk_usuarios PRIMARY KEY (id),
	CONSTRAINT uq_empleado UNIQUE (id_empleado),
	CONSTRAINT uq_nombre UNIQUE (nombre)

);
-- ddl-end --
ALTER TABLE public.usuarios OWNER TO postgres;
-- ddl-end --

-- object: fk_empleados_localidad | type: CONSTRAINT --
-- ALTER TABLE public.empleados DROP CONSTRAINT IF EXISTS fk_empleados_localidad CASCADE;
ALTER TABLE public.empleados ADD CONSTRAINT fk_empleados_localidad FOREIGN KEY (id_localidad)
REFERENCES public.localidades (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_clientes_localidad | type: CONSTRAINT --
-- ALTER TABLE public.clientes DROP CONSTRAINT IF EXISTS fk_clientes_localidad CASCADE;
ALTER TABLE public.clientes ADD CONSTRAINT fk_clientes_localidad FOREIGN KEY (id_localidad)
REFERENCES public.localidades (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_proveedores_localidad | type: CONSTRAINT --
-- ALTER TABLE public.proveedores DROP CONSTRAINT IF EXISTS fk_proveedores_localidad CASCADE;
ALTER TABLE public.proveedores ADD CONSTRAINT fk_proveedores_localidad FOREIGN KEY (id_localidad)
REFERENCES public.localidades (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_compras_proveedores | type: CONSTRAINT --
-- ALTER TABLE public.compras DROP CONSTRAINT IF EXISTS fk_compras_proveedores CASCADE;
ALTER TABLE public.compras ADD CONSTRAINT fk_compras_proveedores FOREIGN KEY (id_proveedor)
REFERENCES public.proveedores (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_detalles_compras_compras | type: CONSTRAINT --
-- ALTER TABLE public.detalles_compras DROP CONSTRAINT IF EXISTS fk_detalles_compras_compras CASCADE;
ALTER TABLE public.detalles_compras ADD CONSTRAINT fk_detalles_compras_compras FOREIGN KEY (id_compra)
REFERENCES public.compras (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_detalles_compras_productos | type: CONSTRAINT --
-- ALTER TABLE public.detalles_compras DROP CONSTRAINT IF EXISTS fk_detalles_compras_productos CASCADE;
ALTER TABLE public.detalles_compras ADD CONSTRAINT fk_detalles_compras_productos FOREIGN KEY (id_producto)
REFERENCES public.productos (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_ventas_empleados | type: CONSTRAINT --
-- ALTER TABLE public.ventas DROP CONSTRAINT IF EXISTS fk_ventas_empleados CASCADE;
ALTER TABLE public.ventas ADD CONSTRAINT fk_ventas_empleados FOREIGN KEY (id_empleado)
REFERENCES public.empleados (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_ventas_clientes | type: CONSTRAINT --
-- ALTER TABLE public.ventas DROP CONSTRAINT IF EXISTS fk_ventas_clientes CASCADE;
ALTER TABLE public.ventas ADD CONSTRAINT fk_ventas_clientes FOREIGN KEY (id_cliente)
REFERENCES public.clientes (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_detalles_ventas_ventas | type: CONSTRAINT --
-- ALTER TABLE public.detalles_ventas DROP CONSTRAINT IF EXISTS fk_detalles_ventas_ventas CASCADE;
ALTER TABLE public.detalles_ventas ADD CONSTRAINT fk_detalles_ventas_ventas FOREIGN KEY (id_ventas)
REFERENCES public.ventas (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_detalles_ventas_productos | type: CONSTRAINT --
-- ALTER TABLE public.detalles_ventas DROP CONSTRAINT IF EXISTS fk_detalles_ventas_productos CASCADE;
ALTER TABLE public.detalles_ventas ADD CONSTRAINT fk_detalles_ventas_productos FOREIGN KEY (id_producto)
REFERENCES public.productos (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_pedidos_empleados | type: CONSTRAINT --
-- ALTER TABLE public.pedidos DROP CONSTRAINT IF EXISTS fk_pedidos_empleados CASCADE;
ALTER TABLE public.pedidos ADD CONSTRAINT fk_pedidos_empleados FOREIGN KEY (id_empleado)
REFERENCES public.empleados (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_pedidos_clientes | type: CONSTRAINT --
-- ALTER TABLE public.pedidos DROP CONSTRAINT IF EXISTS fk_pedidos_clientes CASCADE;
ALTER TABLE public.pedidos ADD CONSTRAINT fk_pedidos_clientes FOREIGN KEY (id_cliente)
REFERENCES public.clientes (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: pk_detalles_ventas_productos | type: CONSTRAINT --
-- ALTER TABLE public.detalles_pedidos DROP CONSTRAINT IF EXISTS pk_detalles_ventas_productos CASCADE;
ALTER TABLE public.detalles_pedidos ADD CONSTRAINT pk_detalles_ventas_productos FOREIGN KEY (id_producto)
REFERENCES public.productos (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_detalles_productos_pedidos | type: CONSTRAINT --
-- ALTER TABLE public.detalles_pedidos DROP CONSTRAINT IF EXISTS fk_detalles_productos_pedidos CASCADE;
ALTER TABLE public.detalles_pedidos ADD CONSTRAINT fk_detalles_productos_pedidos FOREIGN KEY (id_pedido)
REFERENCES public.pedidos (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_usuarios_empleados | type: CONSTRAINT --
-- ALTER TABLE public.usuarios DROP CONSTRAINT IF EXISTS fk_usuarios_empleados CASCADE;
ALTER TABLE public.usuarios ADD CONSTRAINT fk_usuarios_empleados FOREIGN KEY (id_empleado)
REFERENCES public.empleados (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


