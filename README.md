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

## 📦 Instalação e Execução

### Pré-requisitos Obrigatórios
- JDK 17 ou superior instalado
  - Para verificar, abra o terminal/cmd e digite: `java -version`
  - Deve mostrar versão 17 ou superior
- Google Authenticator no celular (ou outro app 2FA compatível)

### Como Obter o Código

1. Clone o repositório ou baixe o código:
```bash
git clone https://github.com/Victormsfalcao/PasswordManager.git
cd PasswordManager
```

2. Ou baixe o ZIP do projeto:
   - Acesse: https://github.com/Victormsfalcao/PasswordManager
   - Clique em "Code" -> "Download ZIP"
   - Extraia em uma pasta de sua preferência

### Como Executar (Forma Simples)

#### Windows:
1. Extraia o arquivo ZIP em uma pasta (se baixou o ZIP)
2. Dê duplo clique no arquivo `run.bat`
3. Siga as instruções na tela

#### Linux/Mac:
1. Extraia o arquivo ZIP em uma pasta (se baixou o ZIP)
2. Abra o terminal na pasta do projeto
3. Execute: `chmod +x run.sh`
4. Execute: `./run.sh`
5. Siga as instruções na tela

### Estrutura de Arquivos Necessária
```
password-manager/
├── lib/                    # Bibliotecas (não mexer)
│   ├── sqlite-jdbc-3.36.0.3.jar
│   ├── commons-codec-1.15.jar
│   └── otp-java-1.3.0.jar
├── src/                    # Código fonte
├── run.bat                 # Script para Windows
├── run.sh                  # Script para Linux/Mac
└── README.md              
```

### Problemas Comuns

1. **Erro "java não é reconhecido"**:
   - O Java não está instalado ou não está no PATH
   - Solução: Instale o JDK 17 ou superior

2. **Erro de compilação**:
   - Verifique se todos os arquivos da pasta `lib` estão presentes
   - Verifique se está usando Java 17 ou superior

3. **Banco de dados não inicializa**:
   - Verifique se tem permissão de escrita na pasta
   - Tente executar como administrador

### Observações Importantes
- Na primeira execução, o programa criará automaticamente o banco de dados
- Guarde sua senha mestra e backup do 2FA em local seguro
- Não delete ou mova os arquivos da pasta `lib`

## 🔒 Primeira Execução

### 1. Configuração da Senha Mestra
- Digite uma senha mestra forte (mínimo 8 caracteres)
- Esta senha será necessária para acessar suas credenciais
- Guarde-a em local seguro - não há recuperação!

### 2. Configuração do Google Authenticator
- Instale o Google Authenticator no seu celular
- Ao executar o programa pela primeira vez, você verá:
  1. Um link para gerar QR code
  2. A chave secreta para digitação manual
  3. Instruções passo a passo para configuração
- Você precisará confirmar a configuração digitando um código
- ⚠️ Guarde o backup dos códigos 2FA em local seguro!

### 3. Teste Inicial
- Após a configuração, o sistema pedirá:
  1. Sua senha mestra
  2. Um código do Google Authenticator
- Se tudo estiver correto, você verá o menu principal

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

### Dicas de Segurança
1. Use uma senha mestra forte e única
2. Mantenha backup do Google Authenticator
3. Não compartilhe sua senha mestra
4. Faça backup regular do banco de dados
5. Mantenha o sistema atualizado

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para:
- Reportar bugs
- Sugerir novas funcionalidades
- Enviar pull requests

## 👤 Autor
* Victor Falcão

## 📝 Licença

MIT License - veja o arquivo [LICENSE](LICENSE) para detalhes.



