

CREATE TABLE IF NOT EXISTS `amenity` (
  `amenity_id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  `description` TEXT
);

-- Junction table for Hotel and Amenity (Many-to-Many)
CREATE TABLE IF NOT EXISTS `hotelamenity` (
  `hotel_id` INT NOT NULL,
  `amenity_id` INT NOT NULL,
  PRIMARY KEY (`hotel_id`, `amenity_id`)
);

-- Junction table for Room and Amenity (Many-to-Many)
CREATE TABLE IF NOT EXISTS `roomamenity` (
  `room_id` INT NOT NULL,
  `amenity_id` INT NOT NULL,
  PRIMARY KEY (`room_id`, `amenity_id`)
);
