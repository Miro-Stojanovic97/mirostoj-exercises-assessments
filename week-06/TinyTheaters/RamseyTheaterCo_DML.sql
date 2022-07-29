-- Miro Stojanovic
-- Theaters Assessment
-- -------------------------------


-- Setup --
USE ramsey_theater;
set sql_safe_updates = 0;
DELETE FROM ticket;
ALTER TABLE ticket auto_increment = 1;
DELETE FROM customer;
ALTER TABLE customer auto_increment = 1;
DELETE FROM theater;
ALTER TABLE theater auto_increment = 1;
set sql_safe_updates = 1;

-- Insert Customer Data --
INSERT INTO customer(
	first_name,
    last_name,
    email,
    phone,
    address
)
	SELECT DISTINCT 
		customer_first,
        customer_last,
        customer_email,
        customer_phone,
        customer_address
	FROM raw_customer;

-- Insert Theater Data --
INSERT INTO theater(
	theater_name,
    address,
    phone,
    email
)
	SELECT DISTINCT 
		theater,
        theater_address,
        theater_phone,
        theater_email
	FROM raw_theater;
    
-- Insert Ticket Data --
INSERT INTO ticket(
	customer_id,
    theater_id,
    seat,
    show_name,
    ticket_price,
    `date`
)
	SELECT DISTINCT 
		c.customer_id,
        t.theater_id,
        rt.seat,
        rt.`show`,
        rt.ticket_price,
        rt.`date`
	FROM raw_ticket rt
		INNER JOIN customer c ON rt.customer_email = c.email
        INNER JOIN theater t ON rt.theater = t.theater_name;
        
        
-- UPDATE 1 -- 
-- Celebrity actor visiting, update ticket price
UPDATE ticket 
SET 
    ticket_price = 22.25
WHERE
    theater_id = (SELECT 
            theater_id
        FROM
            theater
        WHERE
            theater_name = 'Little Fitz')
        AND `date` = '2021-03-01';
        
-- UPDATE 2 -- 
-- Updating seating arrangements, put groups in same row
UPDATE ticket  -- Swap from B4 to C2
SET 
    seat = 'C2'
WHERE
   customer_id = (SELECT 
         customer_id
      FROM
         customer
      WHERE
         first_name = 'Cullen'
            AND last_name = 'Guirau')
      AND seat = 'B4';
        
        
UPDATE ticket  -- Swap from C2 to A4
SET 
    seat = 'A4'
WHERE
    customer_id = (SELECT 
            customer_id
        FROM
            customer
        WHERE
            first_name = 'Chiarra'
                AND last_name = 'Vail')
        AND seat = 'C2';
        

UPDATE ticket  -- Swap from A4 to B4
SET 
    seat = 'B4'
WHERE
    customer_id = (SELECT 
            customer_id
        FROM
            customer
        WHERE
            first_name = 'Pooh'
                AND last_name = 'Bedburrow')
        AND seat = 'A4';
        
        
-- UPDATE 3 --
-- Updating Jammie Swindle's Phone Number
set sql_safe_updates = 0;
UPDATE customer 
SET 
    phone = '1-801-EAT-CAKE' -- Set new number
WHERE
    first_name = 'Jammie'
        AND last_name = 'Swindles';
set sql_safe_updates = 1;


-- DELETE 1 - 
-- Delete single reservation tickets from the 10 Pin.
SELECT group_concat(ticket_id) as ids_to_delete
FROM 
   ticket
GROUP BY customer_id, theater_id
HAVING count(seat) = 1 AND theater_id = (SELECT theater_id FROM theater WHERE theater_name = '10 Pin');

DELETE FROM ticket 
WHERE
    ticket_id IN (SELECT 
        ids_to_delete
    FROM
        (SELECT 
            GROUP_CONCAT(ticket_id) AS ids_to_delete -- Select ticket IDs with <=1 seat and the 10pin's ID
        FROM
            ticket
        GROUP BY customer_id , theater_id
        HAVING COUNT(seat) = 1 -- Select theater ID for the 10pin
            AND theater_id = (SELECT
                theater_id
            FROM
                theater
            
            WHERE
                theater_name = '10 Pin')) t);
                
-- DELETE 2 --
-- Delete customer Liv Egle of Germany
set sql_safe_updates = 0;
DELETE FROM ticket 
WHERE
    customer_id = (SELECT 
        customer_id
    FROM
        customer
    WHERE
        first_name = 'Liv'
        AND last_name = 'Egle of Germany');

DELETE FROM customer
WHERE
   first_name = 'Liv'
    AND last_name = 'Egle of Germany';
set sql_safe_updates = 1;