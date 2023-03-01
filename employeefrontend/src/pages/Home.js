import React,  {useEffect, useState}  from 'react'
import axios from 'axios'
import { Link, useParams } from 'react-router-dom';

export default function Home() {
  
    const [Employee, setEmployee] = useState([]);
    const {id} = useParams();

    useEffect( () => {
      loadEmployee();

      }, []);

      const loadEmployee= async () =>{
        const result= await axios.get("http://localhost:8080/employee/allDetails");
        setEmployee(result.data);
      };

      const deleteEmployee= async(id) =>{
        await axios.delete(`http://localhost:8080/employee/delete/${id}`);
        loadEmployee(id);
      }
    
    

  return (
    <div className='container'>
        <div className='py-4'>
        <table className="table table-success table-striped shadow border">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Employee Id</th>
      <th scope="col">First Name</th>
      <th scope="col">Last Name</th>
      <th scope="col">Email</th>
      
      <th scope="col">Active</th>
      <th scope="col">selectedSkill</th>
      <th scope="col">Age</th>
      <th scope="col">Date of Birth</th>
      <th scope="col">Action</th>

    </tr>
  </thead>
  <tbody>
    {
        Employee.map((employee, index)=>(

    <tr>
      <th scope="row" key={index}>{index+1}</th>
      <td>{employee.employeeId}</td>
      <td>{employee.firstName}</td>
      <td>{employee.lastName}</td>
      <td>{employee.email}</td>
      <td>{employee.active='true'}</td>
      <td>{employee.selectedSkill}</td>
      <td>{employee.age}</td>
      <td>{employee.dob }</td>
      <td>
        <Link className='btn btn-outline-primary mx-2'
        to={`/editemployee/${employee.employeeId}`}
        >edit</Link>
        <button className='btn btn-danger mx-2'
        
        onClick={()=>deleteEmployee(employee.employeeId)}
        >delete</button>
           
      </td>
    </tr>

        ))
    }
    
    
  </tbody>
</table>

<Link className="btn btn-outline-primary mx-2" to='/addemployee'>Add Employee</Link>


        </div>
    </div>
  )
}
