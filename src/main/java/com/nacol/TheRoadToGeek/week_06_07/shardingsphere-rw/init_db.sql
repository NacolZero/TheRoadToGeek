create schema ds_master;
create schema ds_slave0;
create schema ds_slave1;

drop table ds_master.t;
drop table ds_slave0.t;
drop table ds_slave1.t;
CREATE TABLE ds_master.`t` (
                               `id` bigint(11) NOT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE ds_slave0.`t` (
                               `id` bigint(11) NOT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE ds_slave1.`t` (
                               `id` bigint(11) NOT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB;
