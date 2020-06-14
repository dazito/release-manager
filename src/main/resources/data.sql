DROP TABLE IF EXISTS Deploy;
DROP TABLE IF EXISTS SystemVersion;

CREATE TABLE SystemVersion (
  id INT AUTO_INCREMENT PRIMARY KEY,
  ts TIMESTAMP NOT NULL
);

CREATE TABLE Deploy (
  id INT AUTO_INCREMENT PRIMARY KEY,
  systemVersion INT NOT NULL,
  name VARCHAR(90) NOT NULL,
  version INT NOT NULL,
  CONSTRAINT fk_Service_SystemVersion
    FOREIGN KEY (systemVersion)
    REFERENCES SystemVersion (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE INDEX systemVersionIndex ON Deploy(systemVersion);