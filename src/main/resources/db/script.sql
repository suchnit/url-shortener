-- URL Shortener Database Schema

-- Create URL Table
CREATE TABLE url (
    id          BIGSERIAL PRIMARY KEY,
    long_url    TEXT NOT NULL,
    short_code  VARCHAR(10) UNIQUE NOT NULL,
    created_at  TIMESTAMP DEFAULT now(),
    expires_at  TIMESTAMP NULL,
    hits        INT DEFAULT 0,
    user_id     BIGINT NULL,
    status      VARCHAR(10) DEFAULT 'active'
);

-- Indexes for performance
CREATE INDEX idx_short_code ON url (short_code);
CREATE INDEX idx_expires_at ON url (expires_at);

-- Create Analytics Table
CREATE TABLE analytics (
    id           BIGSERIAL PRIMARY KEY,
    short_code   VARCHAR(10) NOT NULL REFERENCES url(short_code) ON DELETE CASCADE,
    ip_address   INET NOT NULL,
    user_agent   TEXT NOT NULL,
    accessed_at  TIMESTAMP DEFAULT now()
);

-- Indexes for analytics queries
CREATE INDEX idx_analytics_short_code ON analytics (short_code);

-- Optional: Create Users Table if user authentication is required
CREATE TABLE users (
    id          BIGSERIAL PRIMARY KEY,
    email       VARCHAR(255) UNIQUE NOT NULL,
    password    TEXT NOT NULL,
    created_at  TIMESTAMP DEFAULT now()
);

-- Foreign key for user_id in URL table
ALTER TABLE url ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL;
