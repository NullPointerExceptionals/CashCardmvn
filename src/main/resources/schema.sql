CREATE TABLE cash_card (
  id INT AUTO_INCREMENT,
  amount DOUBLE,
  owner VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE transaction (
  id INT AUTO_INCREMENT,
  cash_card_id INT,
  amount_added DOUBLE,
  amount_removed DOUBLE,
  FOREIGN KEY (cash_card_id) REFERENCES cash_card(id),
  PRIMARY KEY (id)
);
