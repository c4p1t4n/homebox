USE homebox;

DROP VIEW IF EXISTS chats;
DROP VIEW IF EXISTS avg_rating;
DROP VIEW IF EXISTS services_scheduling;
DROP VIEW IF EXISTS services_status_recent;
DROP VIEW IF EXISTS avg_rating_history;
DROP VIEW IF EXISTS users_rating;


CREATE VIEW
    services_scheduling
AS
SELECT
    price,
    name AS name_service,
    worker_id_user AS worker_id,
    customer_id_user AS customer_id,
    scheduling_status.status_date AS date,
    scheduling_status.service_status AS status,
    service.description AS service_description,
    accomplished_service.description AS address,
    ROW_NUMBER() OVER (ORDER BY accomplished_service.service_date ) AS id
FROM
    service
JOIN
    scheduling
ON
    service.id_service = scheduling.service_id_service AND
    service.deleted IS NULL
JOIN
    accomplished_service
ON
    accomplished_service.scheduling_id_scheduling = scheduling.id_scheduling
JOIN
    scheduling_status
ON
    scheduling_status.scheduling_id_scheduling = scheduling.id_scheduling;

CREATE VIEW
    services_status_recent
AS
SELECT
    price,
    name AS name_service,
    tbl.status_date AS date,
    scheduling.id_scheduling,
    service.deleted AS deleted,
    worker_id_user AS worker_id,
    tbl.service_status AS status,
    customer_id_user AS customer_id,
    service.description AS service_description,
    accomplished_service.description AS address
FROM (
    SELECT
        scheduling_status.*,
        rank() OVER(PARTITION BY scheduling_id_scheduling ORDER BY status_date DESC)  AS rank_status
    FROM
        scheduling_status
) tbl
JOIN
    scheduling
ON
    tbl.scheduling_id_scheduling = scheduling.id_scheduling
LEFT JOIN
    service
ON
    service.id_service = scheduling.service_id_service
LEFT JOIN
    accomplished_service
ON
    accomplished_service.scheduling_id_scheduling = scheduling.id_scheduling
WHERE
    rank_status = 1;


CREATE VIEW
    avg_rating
AS
SELECT
    AVG(rating) AS rating,
    u.id_user AS worker_id
FROM
    user u
JOIN
    service
ON
    u.id_user = service.worker_id_user
JOIN
    scheduling s
ON
    service.id_service = s.service_id_service AND
    service.deleted IS NULL
JOIN
    accomplished_service a
ON
    s.id_scheduling = a.scheduling_id_scheduling
JOIN
    rating r
ON
    a.id_accomplished_service = r.fk_accomplished_service
GROUP BY
    u.id_user;

CREATE VIEW
    avg_rating_history
AS
SELECT
    rating,
    service_date,
    service.id_service,
    scheduling.id_scheduling,
    accomplished_service.description,
    service.worker_id_user AS worker_id
FROM
    service
JOIN
    scheduling
ON
    id_service = service_id_service
JOIN
    accomplished_service
ON
    scheduling_id_scheduling = id_scheduling
JOIN
    rating
ON
    fk_accomplished_service = id_accomplished_service
WHERE
    service.deleted IS NULL
ORDER BY
    id_scheduling;


CREATE VIEW chats
AS
SELECT uhc.*, user_has_chat.user_id_user AS id2
FROM user_has_chat uhc
JOIN user_has_chat ON user_has_chat.chat_id_chat = uhc.chat_id_chat
GROUP BY(uhc.chat_id_chat);

CREATE VIEW
    users_rating
AS
SELECT
    user.*,
    CASE
        WHEN user.type = 'customer' THEN NULL
        ELSE avg_rating.rating
    END
    AS rating
FROM
    user
LEFT JOIN
    avg_rating
ON
    user.id_user = avg_rating.worker_id