CREATE TABLE `team` (
    `id` VARCHAR(36) PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `member` (
    `id` VARCHAR(36) PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `age` INT NOT NULL,
    `phone_number` VARCHAR(20),
    `role` VARCHAR(50) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `team_id` VARCHAR(36),  -- 연관된 team_id 필드 추가
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    CONSTRAINT `fk_member_team` FOREIGN KEY (`team_id`) REFERENCES `team`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `revinfo` (
    `rev` BIGINT AUTO_INCREMENT NOT NULL,
    `revtstmp` BIGINT NULL,
    `updated_by` VARCHAR(36) NULL,
    PRIMARY KEY (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `member_aud` (
    `id` VARCHAR(36) NOT NULL,
    `rev` BIGINT NOT NULL,
    `revtype` TINYINT NOT NULL,

    `password` VARCHAR(255),
    `password_mod` TINYINT(1),
    `name` VARCHAR(100),
    `name_mod` TINYINT(1),
    `age` INT,
    `age_mod` TINYINT(1),
    `phone_number` VARCHAR(20),
    `phone_number_mod` TINYINT(1),
    `role` VARCHAR(50),
    `role_mod` TINYINT(1),
    `team_id` VARCHAR(36),
    `team_mod` TINYINT(1),

    PRIMARY KEY (`id`, `rev`),
    CONSTRAINT `fk_member_aud_revinfo` FOREIGN KEY (`rev`) REFERENCES `revinfo`(`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;