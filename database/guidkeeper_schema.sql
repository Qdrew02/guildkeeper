-- Drop tables in reverse dependency order


DROP TABLE IF EXISTS player_stats CASCADE;
DROP TABLE IF EXISTS player_character CASCADE;
DROP TABLE IF EXISTS stat_definition CASCADE;
DROP TABLE IF EXISTS campaign_membership CASCADE;
DROP TABLE IF EXISTS campaign CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS map_annotation CASCADE;
DROP TABLE IF EXISTS map_tile CASCADE;
DROP TABLE IF EXISTS maps CASCADE;
DROP TABLE IF EXISTS inventory_item CASCADE;
DROP TABLE IF EXISTS quest_update CASCADE;
DROP TABLE IF EXISTS quest CASCADE;

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE campaign (
    campaign_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    creator_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (creator_id) REFERENCES users(user_id)
);

CREATE TABLE campaign_membership (
	membership_id SERIAL PRIMARY KEY,
	user_id INT NOT NULL,
	campaign_id INT NOT NULL,
	role VARCHAR(50) NOT NULL CHECK (role in ('PLAYER', 'GM', 'ADMIN')),
	FOREIGN KEY (user_id) REFERENCES users(user_id),
	FOREIGN KEY (campaign_id) REFERENCES campaign(campaign_id)
);

CREATE TABLE player_character (
	character_id SERIAL PRIMARY KEY,
	campaign_id INT NOT NULL,
	user_id INT NOT NULL,
	role VARCHAR(50) NOT NULL CHECK (role in ('PLAYER', 'GM', 'ADMIN')),
	name VARCHAR (50) NOT NULL,
	level int,
	FOREIGN KEY (campaign_id) REFERENCES campaign(campaign_id),
	FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE stat_definition (
	stat_id SERIAL PRIMARY KEY,
	campaign_id INT NOT NULL,
	name VARCHAR(50) NOT NULL,
	type VARCHAR(20) NOT NULL CHECK (type IN ('INTEGER','DECIMAL','BOOLEAN','STRING','JSON')),
	max_value INT,
	sort_order INT DEFAULT 0,
	is_private BOOLEAN NOT NULL	DEFAULT TRUE,
	category VARCHAR(50),
	FOREIGN KEY (campaign_id) REFERENCES campaign(campaign_id)
);

CREATE TABLE player_stats (
	character_stat_id SERIAL PRIMARY KEY,
	character_id INT NOT NULL,
	stat_id INT NOT NULL,
	current_value INT NOT NULL DEFAULT 0,
	max_value INT NOT NULL DEFAULT 0,
	UNIQUE (character_id, stat_id),
	FOREIGN KEY (stat_id) REFERENCES stat_definition(stat_id),
	FOREIGN KEY (character_id) REFERENCES player_character(character_id)
);

CREATE TABLE maps (
    map_id SERIAL PRIMARY KEY,
    campaign_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    FOREIGN KEY (campaign_id) REFERENCES campaign(campaign_id)
);

CREATE TABLE map_tile (
    map_tile_id SERIAL PRIMARY KEY,
    map_id INT NOT NULL,
    q INT,
    r INT,
    revealed BOOLEAN DEFAULT FALSE,
    region VARCHAR(50),
    faction_control VARCHAR(50),
    FOREIGN KEY (map_id) REFERENCES maps(map_id)
);

CREATE TABLE map_annotation (
    annotation_id SERIAL PRIMARY KEY,
    map_tile_id INT NOT NULL,
    user_id INT NOT NULL,
    is_private BOOLEAN DEFAULT TRUE,
    content TEXT,
    FOREIGN KEY (map_tile_id) REFERENCES map_tile(map_tile_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE inventory_item (
    item_id SERIAL PRIMARY KEY,
    owner_id INT NOT NULL,
    owner_type VARCHAR(20) NOT NULL CHECK (owner_type IN ('CAMPAIGN','CHARACTER')),
    name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    weight DECIMAL(6,2) DEFAULT 0.00,
    description TEXT, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE quests (
    quest_id SERIAL PRIMARY KEY,
    campaign_id INT NOT NULL,
    creator_id INT NOT NULL,
    quest_giver VARCHAR(100) NOT NULL,
    title VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('ACTIVE', 'FAILED', 'COMPLETE', 'INACTIVE')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (campaign_id) REFERENCES campaign(campaign_id),
    FOREIGN KEY (creator_id) REFERENCES users(user_id)
);

CREATE TABLE quest_updates (
    update_id SERIAL PRIMARY KEY,
    quest_id INT NOT NULL,
    user_id INT NOT NULL,
    content TEXT NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (quest_id) REFERENCES quests(quest_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
