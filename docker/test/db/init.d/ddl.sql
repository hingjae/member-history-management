CREATE TABLE `member` (
    `id` VARCHAR(36) PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `age` INT NOT NULL,
    `phone_number` VARCHAR(20),
    `role` VARCHAR(50) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `revinfo` (
    `rev` BIGINT AUTO_INCREMENT NOT NULL,
    `revtstmp` BIGINT NULL,
    PRIMARY KEY (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `member_aud` (
    `id` VARCHAR(36) NOT NULL,
    `rev` BIGINT NOT NULL,
    `revtype` TINYINT NOT NULL,

    `password` VARCHAR(255) NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `age` INT NOT NULL,
    `phone_number` VARCHAR(20),
    `role` VARCHAR(50) NOT NULL,

    PRIMARY KEY (`id`, `rev`),
    CONSTRAINT `fk_member_aud_revinfo` FOREIGN KEY (`rev`) REFERENCES `revinfo`(`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;