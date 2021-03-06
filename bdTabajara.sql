PGDMP     *    1                t            tabajara    9.5.2    9.5.2 E    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           1262    16393    tabajara    DATABASE     �   CREATE DATABASE "tabajara" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE "tabajara";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA "public";
    DROP SCHEMA "public";
             postgres    false            �           0    0    SCHEMA "public"    COMMENT     8   COMMENT ON SCHEMA "public" IS 'standard public schema';
                  postgres    false    6                        3079    12355    plpgsql 	   EXTENSION     C   CREATE EXTENSION IF NOT EXISTS "plpgsql" WITH SCHEMA "pg_catalog";
    DROP EXTENSION "plpgsql";
                  false            �           0    0    EXTENSION "plpgsql"    COMMENT     B   COMMENT ON EXTENSION "plpgsql" IS 'PL/pgSQL procedural language';
                       false    1            �            1259    33580    aeronave    TABLE     �   CREATE TABLE "aeronave" (
    "identificacao" character varying(50) NOT NULL,
    "modelo" character varying(50),
    "capac_passageiros" integer,
    "capac_carga" double precision
);
     DROP TABLE "public"."aeronave";
       public         postgres    false    6            �            1259    40987 	   aeroporto    TABLE     �   CREATE TABLE "aeroporto" (
    "idaeroporto" character varying(50) NOT NULL,
    "nome" character varying(55),
    "cidade" integer
);
 !   DROP TABLE "public"."aeroporto";
       public         postgres    false    6            �            1259    33583    aviao    TABLE     q   CREATE TABLE "aviao" (
    "qtd_turbinas" integer,
    "capac_combust_por_turbina" real
)
INHERITS ("aeronave");
    DROP TABLE "public"."aviao";
       public         postgres    false    183    6            �            1259    33598    carro    TABLE     �   CREATE TABLE "carro" (
    "piloto" character varying(255),
    "cidade_origem" integer,
    "cidade_destino" integer,
    "autonomia" real
)
INHERITS ("aeronave");
    DROP TABLE "public"."carro";
       public         postgres    false    6    183            �            1259    41077 	   cidade_id    SEQUENCE     }   CREATE SEQUENCE "cidade_id"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 987979879798787212
    CACHE 1;
 $   DROP SEQUENCE "public"."cidade_id";
       public       postgres    false    6            �            1259    40973    cidade    TABLE     �   CREATE TABLE "cidade" (
    "idcidade" integer DEFAULT "nextval"('"cidade_id"'::"regclass") NOT NULL,
    "municipio" character varying(70),
    "pais" character varying(50),
    "estado" character varying(50)
);
    DROP TABLE "public"."cidade";
       public         postgres    false    193    6            �            1259    16396    cliente    TABLE     v  CREATE TABLE "cliente" (
    "idcliente" integer NOT NULL,
    "nome" character varying(50),
    "logradouro" character varying(150),
    "numero" integer,
    "bairro" character varying(150),
    "estado" character varying(30),
    "telefone" character varying(30),
    "municipio" character varying(50),
    "cep" character varying(50),
    "cpf" character varying(15)
);
    DROP TABLE "public"."cliente";
       public         postgres    false    6            �            1259    16394    cliente_cod_cliente_seq    SEQUENCE     {   CREATE SEQUENCE "cliente_cod_cliente_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE "public"."cliente_cod_cliente_seq";
       public       postgres    false    182    6            �           0    0    cliente_cod_cliente_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE "cliente_cod_cliente_seq" OWNED BY "cliente"."idcliente";
            public       postgres    false    181            �            1259    33589    helicoptero    TABLE     P   CREATE TABLE "helicoptero" (
    "qtd_helices" integer
)
INHERITS ("aeronave");
 #   DROP TABLE "public"."helicoptero";
       public         postgres    false    6    183            �            1259    65678    id_passagem    SEQUENCE     {   CREATE SEQUENCE "id_passagem"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999988989
    CACHE 1;
 &   DROP SEQUENCE "public"."id_passagem";
       public       postgres    false    6            �            1259    41027    passagem    TABLE     D  CREATE TABLE "passagem" (
    "identificacao" integer DEFAULT "nextval"('"id_passagem"'::"regclass") NOT NULL,
    "cliente" integer,
    "voo" integer,
    "data_venda" "date",
    "hora_venda" time without time zone,
    "preco_final_viagem" double precision,
    "carga_cliente" double precision,
    "status" integer
);
     DROP TABLE "public"."passagem";
       public         postgres    false    197    6            �            1259    33601    piloto    TABLE     `  CREATE TABLE "piloto" (
    "cpf" character varying(15),
    "numerodobreve" character varying(50),
    "logradouro" character varying(255),
    "numero" character varying(50),
    "telefone" character varying,
    "nome" character varying(255),
    "identidade" character varying(15) NOT NULL,
    "cidade" integer,
    "idpiloto" integer NOT NULL
);
    DROP TABLE "public"."piloto";
       public         postgres    false    6            �            1259    41098 	   piloto_id    SEQUENCE     x   CREATE SEQUENCE "piloto_id"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9898989898999
    CACHE 1;
 $   DROP SEQUENCE "public"."piloto_id";
       public       postgres    false    6            �            1259    49272    piloto_identificacao_seq    SEQUENCE     |   CREATE SEQUENCE "piloto_identificacao_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE "public"."piloto_identificacao_seq";
       public       postgres    false    187    6            �           0    0    piloto_identificacao_seq    SEQUENCE OWNED BY     H   ALTER SEQUENCE "piloto_identificacao_seq" OWNED BY "piloto"."idpiloto";
            public       postgres    false    195            �            1259    41017    tipoAeronave    TABLE     �   CREATE TABLE "tipoAeronave" (
    "idTipo" integer NOT NULL,
    "tipo" character varying(50)
)
WITH ("autovacuum_enabled"='true');
 $   DROP TABLE "public"."tipoAeronave";
       public         postgres    false    6            �            1259    49294    voo_id    SEQUENCE     v   CREATE SEQUENCE "voo_id"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 99999999999999
    CACHE 1;
 !   DROP SEQUENCE "public"."voo_id";
       public       postgres    false    6            �            1259    40997    voo    TABLE     P  CREATE TABLE "voo" (
    "id_voo" integer DEFAULT "nextval"('"voo_id"'::"regclass") NOT NULL,
    "tipo" integer NOT NULL,
    "aeronave" character varying(60) NOT NULL,
    "aeroporto_partida" character varying(50) NOT NULL,
    "data_partida" "date" NOT NULL,
    "hora_partida" time without time zone NOT NULL,
    "aeroporto_chegada" character varying(50) NOT NULL,
    "data_chegada" "date" NOT NULL,
    "hora_chegada" time without time zone NOT NULL,
    "lotacao" integer NOT NULL,
    "peso_carga_embarcada" double precision NOT NULL,
    "preco_viagem" double precision NOT NULL
);
    DROP TABLE "public"."voo";
       public         postgres    false    196    6            �           2604    41066 	   idcliente    DEFAULT     t   ALTER TABLE ONLY "cliente" ALTER COLUMN "idcliente" SET DEFAULT "nextval"('"cliente_cod_cliente_seq"'::"regclass");
 F   ALTER TABLE "public"."cliente" ALTER COLUMN "idcliente" DROP DEFAULT;
       public       postgres    false    182    181    182            �           2604    49274    idpiloto    DEFAULT     s   ALTER TABLE ONLY "piloto" ALTER COLUMN "idpiloto" SET DEFAULT "nextval"('"piloto_identificacao_seq"'::"regclass");
 D   ALTER TABLE "public"."piloto" ALTER COLUMN "idpiloto" DROP DEFAULT;
       public       postgres    false    195    187            �          0    33580    aeronave 
   TABLE DATA                     public       postgres    false    183   �J       �          0    40987 	   aeroporto 
   TABLE DATA                     public       postgres    false    189   �J       �          0    33583    aviao 
   TABLE DATA                     public       postgres    false    184   -K       �          0    33598    carro 
   TABLE DATA                     public       postgres    false    186   �K       �          0    40973    cidade 
   TABLE DATA                     public       postgres    false    188   �L       �           0    0 	   cidade_id    SEQUENCE SET     2   SELECT pg_catalog.setval('"cidade_id"', 5, true);
            public       postgres    false    193            �          0    16396    cliente 
   TABLE DATA                     public       postgres    false    182   kM       �           0    0    cliente_cod_cliente_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('"cliente_cod_cliente_seq"', 19, true);
            public       postgres    false    181            �          0    33589    helicoptero 
   TABLE DATA                     public       postgres    false    185   ON       �           0    0    id_passagem    SEQUENCE SET     5   SELECT pg_catalog.setval('"id_passagem"', 12, true);
            public       postgres    false    197            �          0    41027    passagem 
   TABLE DATA                     public       postgres    false    192   	O       �          0    33601    piloto 
   TABLE DATA                     public       postgres    false    187   tP       �           0    0 	   piloto_id    SEQUENCE SET     2   SELECT pg_catalog.setval('"piloto_id"', 9, true);
            public       postgres    false    194            �           0    0    piloto_identificacao_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('"piloto_identificacao_seq"', 2, true);
            public       postgres    false    195            �          0    41017    tipoAeronave 
   TABLE DATA                     public       postgres    false    191   BQ       �          0    40997    voo 
   TABLE DATA                     public       postgres    false    190   �Q       �           0    0    voo_id    SEQUENCE SET     0   SELECT pg_catalog.setval('"voo_id"', 17, true);
            public       postgres    false    196                       2606    40991    aeroporto_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY "aeroporto"
    ADD CONSTRAINT "aeroporto_pkey" PRIMARY KEY ("idaeroporto");
 H   ALTER TABLE ONLY "public"."aeroporto" DROP CONSTRAINT "aeroporto_pkey";
       public         postgres    false    189    189            �           2606    33595 
   aviao_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY "aviao"
    ADD CONSTRAINT "aviao_pkey" PRIMARY KEY ("identificacao");
 @   ALTER TABLE ONLY "public"."aviao" DROP CONSTRAINT "aviao_pkey";
       public         postgres    false    184    184            �           2606    40968 
   carro_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY "carro"
    ADD CONSTRAINT "carro_pkey" PRIMARY KEY ("identificacao");
 @   ALTER TABLE ONLY "public"."carro" DROP CONSTRAINT "carro_pkey";
       public         postgres    false    186    186                       2606    40986    cidade_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY "cidade"
    ADD CONSTRAINT "cidade_pkey" PRIMARY KEY ("idcidade");
 B   ALTER TABLE ONLY "public"."cidade" DROP CONSTRAINT "cidade_pkey";
       public         postgres    false    188    188            �           2606    33597    helicoptero_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY "helicoptero"
    ADD CONSTRAINT "helicoptero_pkey" PRIMARY KEY ("identificacao");
 L   ALTER TABLE ONLY "public"."helicoptero" DROP CONSTRAINT "helicoptero_pkey";
       public         postgres    false    185    185            �           2606    24587 	   idcliente 
   CONSTRAINT     U   ALTER TABLE ONLY "cliente"
    ADD CONSTRAINT "idcliente" PRIMARY KEY ("idcliente");
 A   ALTER TABLE ONLY "public"."cliente" DROP CONSTRAINT "idcliente";
       public         postgres    false    182    182                       2606    49283    identificacao 
   CONSTRAINT     W   ALTER TABLE ONLY "piloto"
    ADD CONSTRAINT "identificacao" PRIMARY KEY ("idpiloto");
 D   ALTER TABLE ONLY "public"."piloto" DROP CONSTRAINT "identificacao";
       public         postgres    false    187    187                       2606    41031    passagem_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY "passagem"
    ADD CONSTRAINT "passagem_pkey" PRIMARY KEY ("identificacao");
 F   ALTER TABLE ONLY "public"."passagem" DROP CONSTRAINT "passagem_pkey";
       public         postgres    false    192    192                       2606    41021    tipoAeronave_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY "tipoAeronave"
    ADD CONSTRAINT "tipoAeronave_pkey" PRIMARY KEY ("idTipo");
 N   ALTER TABLE ONLY "public"."tipoAeronave" DROP CONSTRAINT "tipoAeronave_pkey";
       public         postgres    false    191    191            	           2606    49289    voo_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY "voo"
    ADD CONSTRAINT "voo_pkey" PRIMARY KEY ("id_voo");
 <   ALTER TABLE ONLY "public"."voo" DROP CONSTRAINT "voo_pkey";
       public         postgres    false    190    190            �           1259    49269    fki_cid_destino    INDEX     L   CREATE INDEX "fki_cid_destino" ON "carro" USING "btree" ("cidade_destino");
 '   DROP INDEX "public"."fki_cid_destino";
       public         postgres    false    186            �           1259    49263    fki_cid_origem    INDEX     J   CREATE INDEX "fki_cid_origem" ON "carro" USING "btree" ("cidade_origem");
 &   DROP INDEX "public"."fki_cid_origem";
       public         postgres    false    186                       1259    41129    fki_cidade_aeroporto    INDEX     M   CREATE INDEX "fki_cidade_aeroporto" ON "aeroporto" USING "btree" ("cidade");
 ,   DROP INDEX "public"."fki_cidade_aeroporto";
       public         postgres    false    189                       1259    57506    fki_voo_aviao    INDEX     B   CREATE INDEX "fki_voo_aviao" ON "voo" USING "btree" ("aeronave");
 %   DROP INDEX "public"."fki_voo_aviao";
       public         postgres    false    190                       1259    65677 
   fki_voo_id    INDEX     ?   CREATE INDEX "fki_voo_id" ON "passagem" USING "btree" ("voo");
 "   DROP INDEX "public"."fki_voo_id";
       public         postgres    false    192                       2606    40992    aeroporto_cidade_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "aeroporto"
    ADD CONSTRAINT "aeroporto_cidade_fkey" FOREIGN KEY ("cidade") REFERENCES "cidade"("idcidade");
 O   ALTER TABLE ONLY "public"."aeroporto" DROP CONSTRAINT "aeroporto_cidade_fkey";
       public       postgres    false    189    188    2051                       2606    49264    cid_destino    FK CONSTRAINT     z   ALTER TABLE ONLY "carro"
    ADD CONSTRAINT "cid_destino" FOREIGN KEY ("cidade_destino") REFERENCES "cidade"("idcidade");
 A   ALTER TABLE ONLY "public"."carro" DROP CONSTRAINT "cid_destino";
       public       postgres    false    188    186    2051                       2606    49258 
   cid_origem    FK CONSTRAINT     x   ALTER TABLE ONLY "carro"
    ADD CONSTRAINT "cid_origem" FOREIGN KEY ("cidade_origem") REFERENCES "cidade"("idcidade");
 @   ALTER TABLE ONLY "public"."carro" DROP CONSTRAINT "cid_origem";
       public       postgres    false    2051    186    188                       2606    41124    cidade_aeroporto    FK CONSTRAINT     {   ALTER TABLE ONLY "aeroporto"
    ADD CONSTRAINT "cidade_aeroporto" FOREIGN KEY ("cidade") REFERENCES "cidade"("idcidade");
 J   ALTER TABLE ONLY "public"."aeroporto" DROP CONSTRAINT "cidade_aeroporto";
       public       postgres    false    2051    189    188                       2606    41119    cidade_piloto    FK CONSTRAINT     u   ALTER TABLE ONLY "piloto"
    ADD CONSTRAINT "cidade_piloto" FOREIGN KEY ("cidade") REFERENCES "cidade"("idcidade");
 D   ALTER TABLE ONLY "public"."piloto" DROP CONSTRAINT "cidade_piloto";
       public       postgres    false    188    2051    187                       2606    41032    passagem_cliente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "passagem"
    ADD CONSTRAINT "passagem_cliente_fkey" FOREIGN KEY ("cliente") REFERENCES "cliente"("idcliente");
 N   ALTER TABLE ONLY "public"."passagem" DROP CONSTRAINT "passagem_cliente_fkey";
       public       postgres    false    182    192    2039                       2606    41012    voo_aeroportoChegada_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "voo"
    ADD CONSTRAINT "voo_aeroportoChegada_fkey" FOREIGN KEY ("aeroporto_chegada") REFERENCES "aeroporto"("idaeroporto");
 M   ALTER TABLE ONLY "public"."voo" DROP CONSTRAINT "voo_aeroportoChegada_fkey";
       public       postgres    false    190    189    2053                       2606    41007    voo_aeroportoPartida_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "voo"
    ADD CONSTRAINT "voo_aeroportoPartida_fkey" FOREIGN KEY ("aeroporto_partida") REFERENCES "aeroporto"("idaeroporto");
 M   ALTER TABLE ONLY "public"."voo" DROP CONSTRAINT "voo_aeroportoPartida_fkey";
       public       postgres    false    190    189    2053                       2606    65672    voo_id    FK CONSTRAINT     h   ALTER TABLE ONLY "passagem"
    ADD CONSTRAINT "voo_id" FOREIGN KEY ("voo") REFERENCES "voo"("id_voo");
 ?   ALTER TABLE ONLY "public"."passagem" DROP CONSTRAINT "voo_id";
       public       postgres    false    192    2057    190            �   
   x���          �   v   x���v
Q���WPJL-�/�/*�WR�P�LApu���sSAt2P<%UIS!��'�5XAC��H]GA�;5//��2Դ������"C�� � �)u�.-H-���X��W5�� �O�      �   �   x�͐A�@����a/*xH� :x�@-�ɸk�������֥ȟ���f����E��5�E}�/���G8�F��"5� � X���	i3�R�w\
�;R�=.�S�Fϲ�#�c1����$�7%>\��%��sC7 7]J�8f#�$ cQ���o`�sK|�B-��>�8�YX�Ek[�~��-���"��8o��      �   �   x�͏?�0��~��%-t�?'��`��TI,ڦ$)�񍭠��Ã��܏Wuyj��#��є���Q� McB-U�(�vk�Sh����������nm��Zm�S�W �u8.����@#r��eMBVϓ2���^$�z,I����ě<&Ym��$~���p����g�'��we      �   �   x�����0��>�M�B�B���&Ĕ(���7m������D>�/&����p��t��A6��&��r�8��/��'M#�5�HiU�&4���8^�Uٮ �(��P���X�S�Dnu���ɭp�e�E7`���A��ȧMi�ܽ�Z��߱+yLp���e��c�      �   �   x�͏�
�0E����M\��K\UP,h_���h3%6�~��X��"�������bdY~�2^TR�Vp�����
k��4�h4�WS�����'�] ׊J\Q��Q���t�B4N�+�s�>-������5���)o��;��xB����$"����ϼ�?@b�hZ�'u�`s"
�i�Z��֡�	:� ���ŏph�iGAH�����zW      �   �   x���v
Q���WP�H��L�/(I-�WR�P�LI�+�L�LNLN�W�QP��OI��������S3���ɉE� naIJ<���b%M�0G�P�`u�P�����G�'�24�Q02bMk.�q�c��2����=:
�"X���y�� ��p�:�� kA�&      �   [  x���Mo�@໿b�EM����nO=x 16��+� Z+���ڊZ8/��9L�df_��j��&�r�F���*�Ͼ<2��mv��]����O����J��E[ٚ�$��5���(;�S��E�ˏ搜�i�Ô{�t:U���+oJ>^���L�'�O@�d�X  �P
M�30�fT���l��d�Y,�/��1^H�%Q �$�/)�)�;�p��H�"P�L���Y�%�q�����^$EΊ,E>��*b{���ɻp�Py�4���"������"�������"���{&Iͣ��p!�g��K����g��$�gJ���8�/u�=8t| �l���F?���      �   �   x�͏�
�@��>�e6*��U�!��O��\��OG-z������s��L�
�����	8���Qc�52|h���@�SS����z��Kޠ27
[��q5F���	��q�~���;|ۃM����d������ �ğ�=Z��H�h���39큶\
j|G��r��8�V\�-S'ES,�z9�s;      �   c   x���v
Q���WP*�,�wL-��K,KUR�P�L	�(�@d�4�}B]�4u��<��5��<�2�h��cPF��p��t�q��� ��:N      �   �   x�ݐ�n� �w?bI"a���4Δ!��*�괫uk#)	��V�@]�.}��s�;�r�s}z���|yBx2�5�]Ax�6�ä~�5n4�7��a#�~0�_B��zHB����_�-��z�Qp=4�����Tk�IC�nx�^��/��YA#hu�7F��
� ����Ϲ�������g3�OY��7��$���6�#H�o�?�*N.��V��-�y쒋��=Ia���,�����     