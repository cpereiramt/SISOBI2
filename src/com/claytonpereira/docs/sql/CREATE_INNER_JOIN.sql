select * from registroETurmalina;
SELECT members.`first_name` , members.`last_name` , movies.`title`
FROM members ,movies
WHERE movies.`id` = members.`movie_id`;


