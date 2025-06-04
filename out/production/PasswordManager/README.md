Pronto! Aqui está o arquivo `README.md` gerado para você salvar diretamente.

Vou criar e salvar no seu diretório atual: `/mnt/data/README.md`

````markdown
# Password Manager

Gerenciador de senhas simples e seguro, desenvolvido em Java. Permite armazenar credenciais (serviço, usuário, senha) com criptografia, autenticação de dois fatores (2FA) via TOTP e verificação de vazamento de senhas usando APIs externas.

---

## Funcionalidades

- Cadastro de credenciais com criptografia AES das senhas.
- Listagem das credenciais com senha descriptografada.
- Autenticação de dois fatores (2FA) baseada em TOTP para aumentar a segurança.
- Verificação se uma senha já foi exposta em vazamentos públicos.
- Banco de dados SQLite para armazenamento local.

---

## Tecnologias e Bibliotecas

- Java 17+
- SQLite (via `sqlite-jdbc`)
- Apache Commons Codec (para manipulação Base32)
- OTP-Java (TOTP - Time-Based One-Time Password)
- JDBC para conexão com banco SQLite

---

## Como usar

### Preparação

1. Clone este repositório:
   ```bash
   git clone https://github.com/seuusuario/password-manager.git
   cd password-manager
````

2. Certifique-se de ter o JDK instalado (Java 17 ou superior).

3. Baixe as dependências no diretório `lib/`:

    * `sqlite-jdbc-3.36.0.3.jar`
    * `commons-codec-1.15.jar`
    * `otp-java-1.3.0.jar`

### Compilar

Compile o projeto usando:

```bash
javac -cp ".;lib/*" -d bin src\Main.java src\controller\*.java src\db\*.java src\model\*.java src\security\*.java src\utils\*.java
```

### Executar

Execute o programa com:

```bash
java -cp ".;bin;lib/*" src.Main
```

---

## Testar verificação de vazamento

Na execução, use a opção de verificar se uma senha está comprometida. Teste com senhas comuns para ver a resposta.

---

## Estrutura do projeto

* `src/` — Código-fonte Java organizado por pacotes:

    * `controller` — Lógica de controle das credenciais e autenticação
    * `db` — Helper de conexão e inicialização do banco SQLite
    * `model` — Classes modelo, como `Credential`
    * `security` — Criptografia e 2FA
    * `utils` — Utilitários diversos, como verificação de vazamento

* `lib/` — Bibliotecas externas necessárias

* `bin/` — Código compilado

---

## Contribuição

Contribuições são bem-vindas! Abra issues para bugs ou novas funcionalidades, e faça pull requests.

---

## Licença

MIT License — sinta-se livre para usar, modificar e distribuir.

```

Vou salvar o arquivo para você.
```


It seems like I can’t do more advanced data analysis right now. Please try again later.

Mas aqui está o conteúdo do README.md para você copiar e colar direto no seu arquivo:

````markdown
# Password Manager

Gerenciador de senhas simples e seguro, desenvolvido em Java. Permite armazenar credenciais (serviço, usuário, senha) com criptografia, autenticação de dois fatores (2FA) via TOTP e verificação de vazamento de senhas usando APIs externas.

---

## Funcionalidades

- Cadastro de credenciais com criptografia AES das senhas.
- Listagem das credenciais com senha descriptografada.
- Autenticação de dois fatores (2FA) baseada em TOTP para aumentar a segurança.
- Verificação se uma senha já foi exposta em vazamentos públicos.
- Banco de dados SQLite para armazenamento local.

---

## Tecnologias e Bibliotecas

- Java 17+
- SQLite (via `sqlite-jdbc`)
- Apache Commons Codec (para manipulação Base32)
- OTP-Java (TOTP - Time-Based One-Time Password)
- JDBC para conexão com banco SQLite

---

## Como usar

### Preparação

1. Clone este repositório:
   ```bash
   git clone https://github.com/seuusuario/password-manager.git
   cd password-manager
````

2. Certifique-se de ter o JDK instalado (Java 17 ou superior).

3. Baixe as dependências no diretório `lib/`:

    * `sqlite-jdbc-3.36.0.3.jar`
    * `commons-codec-1.15.jar`
    * `otp-java-1.3.0.jar`

### Compilar

Compile o projeto usando:

```bash
javac -cp ".;lib/*" -d bin src\\Main.java src\\controller\\*.java src\\db\\*.java src\\model\\*.java src\\security\\*.java src\\utils\\*.java
```

### Executar

Execute o programa com:

```bash
java -cp ".;bin;lib/*" src.Main
```

---

## Testar verificação de vazamento

Na execução, use a opção de verificar se uma senha está comprometida. Teste com senhas comuns para ver a resposta.

---

## Estrutura do projeto

* `src/` — Código-fonte Java organizado por pacotes:

    * `controller` — Lógica de controle das credenciais e autenticação
    * `db` — Helper de conexão e inicialização do banco SQLite
    * `model` — Classes modelo, como `Credential`
    * `security` — Criptografia e 2FA
    * `utils` — Utilitários diversos, como verificação de vazamento

* `lib/` — Bibliotecas externas necessárias

* `bin/` — Código compilado

---

## Contribuição

Contribuições são bem-vindas! Abra issues para bugs ou novas funcionalidades, e faça pull requests.

---

## Licença

MIT License — sinta-se livre para usar, modificar e distribuir.

```

Se quiser, posso ajudar a criar outros arquivos ou formatos!
```
