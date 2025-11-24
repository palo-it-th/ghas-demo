package com.lab.ghas.config;

/**
 * INSECURE CONFIGURATION - Hardcoded secrets for demo
 * Secret Scanning should detect these patterns
 */
public class SecretsConfig {
    
    // API Keys and Tokens
    public static final String GITHUB_TOKEN = "ghp_xF8k3L9mP2nQ5rT6vW7yZ8aB1cD4eF5gH6i";
    public static final String STRIPE_API_KEY = "sk_live_51AbCdEfGhIjKlMnOpQrStUvWxYz1234567890";
    public static final String OPENAI_API_KEY = "sk-proj-AbCdEfGhIjKlMnOpQrStUvWxYz1234567890";
    
    // Cloud Provider Credentials
    public static final String AWS_ACCESS_KEY = "AKIAIOSFODNN7EXAMPLE";
    public static final String AWS_SECRET_KEY = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
    public static final String AZURE_KEY = "AbCdEfGhIjKlMnOpQrStUvWxYz1234567890==";
    
    // Database Credentials
    public static final String DB_CONNECTION = "postgresql://admin:MyP@ssw0rd123!@prod-db.example.com:5432/app";
    public static final String MONGO_URI = "mongodb+srv://root:SuperSecret123@cluster.mongodb.net/prod";
    
    // Third-party Service Keys
    public static final String SENDGRID_KEY = "SG.AbCdEfGhIjKlMnOpQrStUv.WxYz1234567890";
    public static final String TWILIO_SID = "ACabcdef1234567890abcdef1234567890";
    public static final String TWILIO_TOKEN = "abcdef1234567890abcdef1234567890";
    
    // Slack Integration
    public static final String SLACK_WEBHOOK = "https://hooks.slack.com/services/T00000000/B00000000/XXXXXXXXXXXXXXXXXXXX";
    public static final String SLACK_BOT_TOKEN = "xoxb-1234567890-1234567890123-AbCdEfGhIjKlMnOpQrStUvWx";
    
    // Private Keys (Partial for demo)
    public static final String RSA_PRIVATE_KEY = 
        "-----BEGIN RSA PRIVATE KEY-----\n" +
        "MIIEpAIBAAKCAQEA1234567890...\n" +
        "-----END RSA PRIVATE KEY-----";
    
    // Encryption & Signing Keys
    public static final String JWT_SECRET = "my-super-secret-jwt-key-for-production-use-only";
    public static final String ENCRYPTION_KEY = "AES256-key-that-should-never-be-hardcoded";
    
    // Container Registry Tokens
    public static final String DOCKER_TOKEN = "dckr_pat_AbCdEfGhIjKlMnOpQrStUvWxYz1234";
    public static final String NPM_TOKEN = "npm_AbCdEfGhIjKlMnOpQrStUvWxYz1234567890";
    
    // Monitoring & Logging
    public static final String DATADOG_KEY = "abcdef1234567890abcdef1234567890";
    public static final String NEW_RELIC_KEY = "abcdef1234567890abcdef1234567890abcdef12";
}
