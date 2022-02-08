select *
from student
where student.age >= 10 and student.age <= 20;

select student.name
from student;

select *
from student
where name like '%a%';

select *
from student
where student.age < student.id;

select *
from student
order by student.age;

