ALTER TABLE city ADD COLUMN ulbgrade VARCHAR(20) NOT NULL DEFAULT 'foo';
ALTER TABLE city ALTER COLUMN ulbgrade DROP DEFAULT;