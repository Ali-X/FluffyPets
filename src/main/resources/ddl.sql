CREATE TABLE IF NOT EXISTS `Pets`.`categories` (
  `id`             INT         NOT NULL AUTO_INCREMENT,
  `categoryName`   VARCHAR(32) NOT NULL UNIQUE,
  `categoryNameUa` VARCHAR(32),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);

CREATE TABLE IF NOT EXISTS `Pets`.`users` (
  `id`         INT          NOT NULL AUTO_INCREMENT,
  `userName`   VARCHAR(32)  NOT NULL UNIQUE,
  `password`   VARCHAR(32)  NOT NULL,
  `email`      VARCHAR(128) NOT NULL UNIQUE,
  `roleString` VARCHAR(32)  NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);

CREATE TABLE IF NOT EXISTS `Pets`.`products` (
  `id`          INT          NOT NULL AUTO_INCREMENT,
  `productName` VARCHAR(128) NOT NULL,
  `producer`    VARCHAR(128),
  `price`       VARCHAR(12)  NOT NULL,
  `description` VARCHAR(500),
  `pictureURL`  VARCHAR(128),
  `categoryId`  INT          NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT FOREIGN KEY (categoryId) REFERENCES categories (id)
);

CREATE TABLE IF NOT EXISTS `Pets`.`orders` (
  `id`             INT         NOT NULL AUTO_INCREMENT,
  `userId`         INT         NOT NULL,
  `dateOfOrder`    DATE        NOT NULL,
  `orderStatus`    VARCHAR(16) NOT NULL,
  `dateOfDelivery` DATE,
  `comment`        VARCHAR(128),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT FOREIGN KEY (userId) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS `Pets`.`ordersItems` (
  `id`        INT         NOT NULL AUTO_INCREMENT,
  `orderId`   INT         NOT NULL,
  `productId` INT,
  `quantity`  INT         NOT NULL,
  `price`     VARCHAR(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT FOREIGN KEY (orderId) REFERENCES orders (id),
  CONSTRAINT FOREIGN KEY (productId) REFERENCES products (id)
);

CREATE TABLE IF NOT EXISTS `Pets`.`userAdress` (
  `id`       INT          NOT NULL AUTO_INCREMENT,
  `userId`   INT          NOT NULL,
  `fullName` VARCHAR(100) NOT NULL,
  `district` VARCHAR(30)  NOT NULL,
  `area`     VARCHAR(30)  NOT NULL,
  `street`   VARCHAR(30)  NOT NULL,
  `app`      VARCHAR(30)  NOT NULL,
  `phone`    VARCHAR(20)  NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT FOREIGN KEY (userId) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS `Pets`.`courier` (
  id             INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name           VARCHAR(45)     NOT NULL,
  surname        VARCHAR(45)     NOT NULL,
  passportNumber VARCHAR(45)     NOT NULL
);

CREATE TABLE IF NOT EXISTS `Pets`.`storage` (
  id            INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  productId     INT             NOT NULL,
  avaliableHere INT             NOT NULL DEFAULT 0,
  reservedHere  INT             NOT NULL DEFAULT 0,
  status        VARCHAR(45),
  CONSTRAINT storage_products_id_fk FOREIGN KEY (productId) REFERENCES products (id)
);

CREATE TABLE IF NOT EXISTS `Pets`.`delivery`
(
  id        INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  courierId INT             NOT NULL,
  orderId   INT             NOT NULL,
  status    VARCHAR(45)     NOT NULL,
  start     DATE            NOT NULL,
  finish    DATE,
  CONSTRAINT delivery_courier_id_fk FOREIGN KEY (courierId) REFERENCES courier (id),
  CONSTRAINT delivery_orders_id_fk FOREIGN KEY (orderId) REFERENCES orders (id)
);