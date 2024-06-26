package br.com.prosperah.api.appcore.constants;

public class Constants {

    public static final String USUARIO_EXISTENTE_TABELAS_CADASTRAIS = "Usuário ja existente nas tabelas cadastrais";
    public static final String EMAIL_EXISTENTE_TABELAS_CADASTRAIS = "Endereço de email já existente nas tabelas cadastrais";
    public static final String USUARIO_CRIADO = "Usuário {} salvo na tabela de cadastro";
    public static final String EMAIL_INVALIDO = "Email inválido, favor informar um valido";
    public static final String REGISTRANDO_USUARIO_EMAIL_VERIFICACAO = "Usuário disponivel para verificação de email. Enviando para [%s]...";

    // Outras constantes aqui...

    private Constants() {
    }

    public static class EmailConstants {
        public static final String EMAIL_ENVIADO = "Email enviado para {}";
        public static final String EMAIL_ERRO = "Erro ao enviar email: {}";

        private EmailConstants() {
        }
    }
}
