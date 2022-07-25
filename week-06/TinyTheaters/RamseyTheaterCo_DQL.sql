-- Miro Stojanovic
-- Theaters Assessment
-- -------------------------------


-- Setup --
use ramsey_theater;


-- QUERY 1/12 --
-- Find all performances in the last quarter of 2021 (Oct. 1, 2021 - Dec. 31 2021).
SELECT
   show_name,
   `date`
FROM
   ticket
WHERE
   `date` BETWEEN '2021-10-01' AND '2021-12-31'
GROUP BY show_name, `date`;


-- QUERY 2/12 --
-- List customers without duplication.
SELECT
   *
FROM
   customer;


-- QUERY 3/12 --
-- Find all customers without a .com email address.
SELECT
   *
FROM
   customer
WHERE
   email NOT LIKE '%.com';
   
   
-- QUERY 4/12 --
-- Find the three cheapest shows.
SELECT DISTINCT
   show_name, ticket_price
FROM
   ticket
ORDER BY ticket_price ASC
LIMIT 3;
   
   
-- QUERY 5/12 --
-- List customers and the show they're attending with no duplication. change order of join statements
SELECT
    CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
    show_name
FROM
    ticket t
        INNER JOIN
    customer c ON c.customer_id = t.customer_id
GROUP BY customer_name, show_name;
    
    
-- QUERY 6/12 --
-- List customer, show, theater, and seat number in one query.
SELECT 
    CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
    show_name,
    th.theater_name,
    seat
FROM
    ticket t
        INNER JOIN
    customer c ON c.customer_id = t.customer_id
        INNER JOIN
    theater th ON th.theater_id = t.theater_id;


-- QUERY 7/12
-- Find customers without an address.
SELECT
   *
FROM
   customer
WHERE
   address = '';


-- QUERY 8/12 --
-- Recreate the spreadsheet data with a single query.
SELECT 
    c.first_name,
    c.last_name,
    c.email,
    c.phone,
    c.address,
    seat,
    show_name,
    ticket_price,
    `date`,
    th.theater_name,
    th.address,
    th.phone,
    th.email
FROM
    ticket t
        INNER JOIN
    customer c ON c.customer_id = t.customer_id
        INNER JOIN
    theater th ON th.theater_id = t.theater_id;


-- QUERY 9/12 --
-- Count total tickets purchased per customer.
SELECT
   c.first_name, c.last_name, count(ticket_id) as ticket_count
FROM 
   ticket t
      INNER JOIN
   customer c ON c.customer_id = t.customer_id
GROUP BY
   t.customer_id;


-- QUERY 10/12 --
-- Calculate the total revenue per show based on tickets sold.
SELECT 
    show_name,
    COUNT(ticket_id) AS tickets_sold,
    ticket_price,
    COUNT(ticket_id) * ticket_price AS total_revenue
FROM
    ticket t
GROUP BY show_name, ticket_price
ORDER BY total_revenue DESC;


-- QUERY 11/12 --
-- Calculate the total revenue per theater based on tickets sold.
SELECT 
    th.theater_name,
    COUNT(t.theater_id) AS tickets_sold,
    SUM(ticket_price) AS ticket_revenue
FROM
    ticket t
        INNER JOIN
    theater th ON th.theater_id = t.theater_id
GROUP BY t.theater_id;


-- QUERY 12/12 --
-- Who is the biggest supporter of RCTTC? Who spent the most in 2021?
SELECT 
    c.first_name, 
    c.last_name, 
    COUNT(t.customer_id) AS tickets_purhcased, 
    SUM(ticket_price) AS total_spent
FROM
    ticket t
      INNER JOIN
   customer c ON c.customer_id = t.customer_id
GROUP BY c.first_name, c.last_name
ORDER BY total_spent DESC
LIMIT 3;