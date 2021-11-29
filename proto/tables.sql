BEGIN;

CREATE TABLE users (
    user_id INTEGER PRIMARY KEY,
    username TEXT NOT NULL UNIQUE,
    email TEXT NOT NULL UNIQUE,
    status_v INTEGER NOT NULL DEFAULT 0 CHECK(status_v >= 0 AND status_v <= 2),
    status_msg TEXT,
    default_profile_access INTEGER NOT NULL DEFAULT -1 CHECK(default_profile_access >= -1),
    iconset_id INTEGER DEFAULT -1
);

CREATE TABLE games (
    game_id INTEGER PRIMARY KEY,
    title TEXT NOT NULL,
    gm INTEGER NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (gm) REFERENCES users (user_id)
);

CREATE TABLE user_relations (
    user1 INTEGER NOT NULL,
    user2 INTEGER NOT NULL,
    relation INTEGER NOT NULL DEFAULT -1,
    FOREIGN KEY (user1) REFERENCES users (user_id),
    FOREIGN KEY (user2) REFERENCES users (user_id)
);

CREATE TABLE game_members(
    user_id INTEGER NOT NULL,
    game_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (game_id) REFERENCES games (game_id)
);

CREATE TABLE asset_types(
    type_id INTEGER PRIMARY KEY,
    type_name TEXT NOT NULL UNIQUE
);

CREATE TABLE assets(
    asset_id INTEGER PRIMARY KEY,
    type_id INTEGER NOT NULL,
    asset_name TEXT NOT NULL UNIQUE
    FOREIGN KEY (type_id) REFERENCES asset_types (type_id)
);

END;