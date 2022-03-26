select student.name as student,
       student.age,
       faculty.name as faculty,
       student.avatar_id
from student
         inner join faculty on student.faculty_id = faculty.id;

select student.name as student,
       student.age,
       faculty.name as faculty
from student
         inner join faculty on student.faculty_id = faculty.id
where student.avatar_id is not null;
