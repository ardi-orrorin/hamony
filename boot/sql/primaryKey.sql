ALTER TABLE `TBL_DIARY` ADD CONSTRAINT `PK_TBL_DIARY` PRIMARY KEY (
    `ID`
    );

ALTER TABLE `TBL_MEMBER` ADD CONSTRAINT `PK_TBL_MEMBER` PRIMARY KEY (
    `ID`
    );

ALTER TABLE `TBL_TAG` ADD CONSTRAINT `PK_TBL_TAG` PRIMARY KEY (
    `ID`
    );

ALTER TABLE `TBL_DIARY_TAG` ADD CONSTRAINT `PK_TBL_DIARY_TAG` PRIMARY KEY (
    `ID`
    );

ALTER TABLE `TBL_URL` ADD CONSTRAINT `PK_TBL_URL` PRIMARY KEY (
    `ID`
    );

ALTER TABLE `TBL_FILE` ADD CONSTRAINT `PK_TBL_FILE` PRIMARY KEY (
    `ID`
    );

ALTER TABLE `TBL_SCORE` ADD CONSTRAINT `PK_TBL_SCORE` PRIMARY KEY (
    `ID`
    );

ALTER TABLE `TBL_LIKE` ADD CONSTRAINT `PK_TBL_LIKE` PRIMARY KEY (
    `ID`
    );

ALTER TABLE `TBL_FAV_DIARY` ADD CONSTRAINT `PK_TBL_FAV_DIARY` PRIMARY KEY (
    `ID`
    );

ALTER TABLE `TBL_ALERT` ADD CONSTRAINT `PK_TBL_ALERT` PRIMARY KEY (
    `ID`
    );

ALTER TABLE `TBL_ALERT_TEMPLATE` ADD CONSTRAINT `PK_TBL_ALERT_TEMPLATE` PRIMARY KEY (
    `ID`
    );