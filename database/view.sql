USE homebox;

CREATE VIEW services_scheduling
AS
SELECT ROW_NUMBER() OVER (ORDER BY accomplished_service.service_date ) AS id,
worker_id_user AS worker_id, customer_id_user AS customer_id, name AS name_service, 
service.description AS service_description, accomplished_service.description AS address,price, 
scheduling_status.status_date AS date, scheduling_status.service_status AS status 
FROM service JOIN scheduling
             ON service.id_service = scheduling.service_id_service 
             JOIN accomplished_service 
             ON accomplished_service.scheduling_id_scheduling = scheduling.id_scheduling 
             JOIN scheduling_status 
             ON scheduling_status.scheduling_id_scheduling = scheduling.id_scheduling;

CREATE VIEW services_status_recent
AS
SELECT worker_id_user AS worker_id, customer_id_user AS customer_id, scheduling.id_scheduling, name AS name_service, 
service.description AS service_description, accomplished_service.description AS address,price, 
tbl.status_date AS date, tbl.service_status AS status  
FROM (SELECT * ,rank() OVER(PARTITION BY scheduling_id_scheduling ORDER BY status_date DESC)  AS rank_status
      FROM scheduling_status) tbl JOIN scheduling 
                                  ON tbl.scheduling_id_scheduling = scheduling.id_scheduling  
                                  LEFT JOIN service on service.id_service = scheduling.service_id_service
                                  LEFT JOIN accomplished_service 
                                  ON accomplished_service.scheduling_id_scheduling = scheduling.id_scheduling
WHERE rank_status = 1;        


CREATE VIEW avg_rating
AS
SELECT AVG(rating) AS rating, u.id_user AS worker_id 
FROM user u INNER JOIN service 
            ON u.id_user = service.worker_id_user 
            JOIN scheduling s 
            ON service.id_service = s.service_id_service 
            INNER JOIN accomplished_service a 
            ON s.id_scheduling = a.scheduling_id_scheduling 
            INNER JOIN rating r 
            ON a.id_accomplished_service = r.fk_accomplished_service 
GROUP BY u.id_user;

CREATE VIEW avg_rating_history
AS
SELECT service.worker_id_user AS worker_id, service.id_service, scheduling.id_scheduling, 
service_date, accomplished_service.description, rating 
FROM service JOIN scheduling ON id_service = service_id_service 
             JOIN accomplished_service 
             ON scheduling_id_scheduling = id_scheduling 
             JOIN rating 
             ON fk_accomplished_service = id_accomplished_service 
ORDER BY(id_scheduling);


CREATE VIEW chats
AS
SELECT uhc.*, user_has_chat.user_id_user AS id2 
FROM user_has_chat uhc 
JOIN user_has_chat ON user_has_chat.chat_id_chat = uhc.chat_id_chat 
GROUP BY(uhc.chat_id_chat);
