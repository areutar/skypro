select student.name as student,
       student.age,
       faculty.name as faculty,
       student.avatar_id
from student
         join faculty on student.faculty_id = faculty.id;

select student.name as student,
       student.age
from student
where student.avatar_id is not null;
