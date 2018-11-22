/*


CREATE TABLE tags (
tag VARCHAR(255) NOT NULL,
PRIMARY KEY (tag)
);

CREATE TABLE purchases (
purchaseId INT AUTO_INCREMENT,
purchaseType VARCHAR(255),
purchaseDate DATETIME,
purchaseLocation VARCHAR(255),
purchaseItems VARCHAR(500),
receiptKept BOOLEAN,
receiptCost REAL,
actualCost REAL,
notesAndWarranty VARCHAR(500),
PRIMARY KEY (purchaseId),
FOREIGN KEY (purchaseType) REFERENCES purchaseTypes(type)
);

CREATE TABLE purchaseTags (
tag VARCHAR(255),
purchaseId INT,
PRIMARY KEY (tag, purchaseId),
FOREIGN KEY (tag) REFERENCES tags(tag),
FOREIGN KEY (purchaseId) REFERENCES purchases(purchaseId)
);
CREATE TABLE purchaseTypes (
type Varchar(255),
PRIMARY KEY (type)
);

*/

#INSERT INTO tags VALUES ("Woolworths Rewards $10"), ("Woolworths Giftcard");
#INSERT INTO purchaseTypes VALUES ('Food'),('Bill'),('Entertainment'),('One-Off'),('Other'),('Transport');