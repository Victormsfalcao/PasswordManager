# Password Manager

Gerenciador de senhas seguro desenvolvido em Java. Oferece armazenamento criptografado de credenciais com múltiplas camadas de segurança, incluindo autenticação de dois fatores (2FA), verificação de vazamentos e criptografia AES-GCM.

## 🔐 Funcionalidades

### Segurança
- **Criptografia AES-GCM** para todas as senhas armazenadas
- **Autenticação de dois fatores (2FA)** via Google Authenticator
- **Verificação de vazamentos** usando a API do Have I Been Pwned
- **Senha mestra** para acesso ao sistema
- **Proteção contra remoção acidental** de credenciais

### Gerenciamento de Credenciais
- Adicionar novas credenciais (serviço, usuário, senha)
- Listar credenciais com senhas criptografadas
- Remover credenciais com autenticação dupla
- Gerador de senhas seguras

### Banco de Dados
- SQLite local para armazenamento seguro
- Estrutura otimizada para credenciais
- Backup automático do banco de dados

## 🛠️ Tecnologias

- Java 17+
- SQLite (via `sqlite-jdbc`)
- Apache Commons Codec (Base32 para 2FA)
- Criptografia AES-GCM
- API Have I Been Pwned

## 📦 Instalação

### Pré-requisitos
- JDK 17 ou superior
- Git (opcional)

### Configuração

1. Clone o repositório ou baixe o código:
```bash
git clone https://github.com/seuusuario/password-manager.git
cd password-manager
```

2. Certifique-se de ter as dependências no diretório `lib/`:
- `sqlite-jdbc-3.36.0.3.jar`
- `commons-codec-1.15.jar`

### Compilação
```bash
javac -cp ".;lib/*" -d bin @sources.txt
```

### Execução
```bash
java -cp ".;bin;lib/*" src.Main
```

## 🔒 Primeira Execução

1. Configure sua senha mestra (mínimo 8 caracteres)
2. Configure o Google Authenticator com o código QR fornecido
3. Guarde sua senha mestra em local seguro

## 🎯 Uso

### Menu Principal
1. **Adicionar credencial**
   - Cadastre novas senhas com serviço e usuário
   - Senhas são automaticamente criptografadas

2. **Listar credenciais**
   - Visualize todas as credenciais salvas
   - Senhas são mostradas em formato criptografado

3. **Remover credencial**
   - Requer senha mestra
   - Requer código 2FA
   - Confirmação dupla para evitar remoções acidentais

4. **Gerar senha segura**
   - Cria senhas fortes automaticamente
   - Combina letras, números e símbolos

5. **Verificar vazamento de senha**
   - Verifica se uma senha já vazou
   - Usa API segura do Have I Been Pwned
   - Não envia a senha completa para verificação

## 🔐 Segurança

### Criptografia
- Algoritmo: AES-GCM
- Chaves derivadas com PBKDF2
- Salt único por senha
- Vetores de inicialização (IV) aleatórios

### Autenticação
- Senha mestra com hash seguro
- 2FA via Google Authenticator
- Proteção contra tentativas repetidas

### Armazenamento
- Senhas nunca são salvas em texto puro
- Banco de dados local criptografado
- Sem envio de dados para servidores externos

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para:
- Reportar bugs
- Sugerir novas funcionalidades
- Enviar pull requests

## 👤 Autor
* Victor Falcão

## 📝 Licença

MIT License - veja o arquivo [LICENSE](LICENSE) para detalhes.



