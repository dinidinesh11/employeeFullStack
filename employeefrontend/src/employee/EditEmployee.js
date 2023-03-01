import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';

export default function EditEmployee() {

    let navigate = useNavigate();

    const {id}=useParams();

     

    const [skillName, setSkillName] = useState([]);
   

    const [employee, setEmployee]= useState({
        employeeId:"",
        firstName: "",
        lastName:"",
        email:"",
        dob:"",
        active:"",
        selectedSkill:"",
        age:""

    })

    const{firstName, lastName, email, selectedSkill, dob, age}=employee

   

    

    useEffect(() => {  
          axios.get('http://localhost:8080/skills/list')
        .then(response => setSkillName(response.data)) 
        .catch(error => console.log(error));
      }, []);


    const onInputChange=(e)=>{
        const value = e.target.value;
        setEmployee({...employee, [e.target.name]:value});
    };
     useEffect(()=>{
         loadEmployee();
     },[]);

     

    const onSubmit= async (e)=>{
        e.preventDefault();
        await axios.put(`http://localhost:8080/employee/update/${id}`, employee);
        navigate("/home");


    };

     const loadEmployee = async()=>{
         const result=await axios.get(`http://localhost:8080/employee/getOne/${id}`);
         setEmployee(result.data);
    }

  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                <h2 className='text-center m-4'>Edit Employee</h2>
                <form onSubmit={(e)=> onSubmit(e)}>
                <div className='mb-3'>
                    <label htmlFor='firstName' className='form-label'>
                        First Name
                    </label>
                    <input
                    type={"text"}
                    className='form-control'
                    placeholder='Enter First Name'
                    name='firstName'
                    value={firstName}
                    onChange={(e)=>onInputChange(e)}
                   required />
                </div>
                <div className='mb-3'>
                    <label htmlFor='lastName' className='form-label'>
                        Last Name
                    </label>
                    <input
                    type={"text"}
                    className='form-control'
                    placeholder='Enter Last Name'
                    name='lastName'
                    value={lastName}
                    onChange={(e)=>onInputChange(e)}
                  required  />
                </div>
                <div className='mb-3'>
                    <label htmlFor='email' className='form-label'>
                        Email Id
                    </label>
                    <input
                    type={'email'}
                    className='form-control'
                    placeholder='Enter EmailId'
                    name='email'
                    value={email}
                    onChange={(e)=>onInputChange(e)}
                    required/>
                </div>
                <div className='mb-3'>
                    <label htmlFor='age' className='form-label'>
                        Age
                    </label>
                    <input
                    type={'number'}
                    className='form-control'
                    placeholder='Enter age'
                    name='age'
                    min={'18'}
                    max={'45'}
                    value={age }
                    onChange={(e)=>onInputChange(e)}
                    />
                </div>
                <div className='mb-3'>
                    <label htmlFor='dob' className='form-label'>
                        Date of Birth
                    </label>
                    <input
                    type={'date'}
                    className='form-control'
                    placeholder='Enter DOB'
                    name='dob'
                    max={'2003-03-01'}
                    value={dob}
                    onChange={(e)=>onInputChange(e)}
                    />
                   
                    <div className='mb-3'>
                    <label htmlFor="skill-select">Select a skill:</label>
                   
                          <select id="skill-select"
                          className='form-control'
                          placeholder='select skill'
                          name='selectedSkill'
                           value={selectedSkill}
                           onChange={(e)=>onInputChange(e)}>
                            {skillName.map(skillName => ( 
                            <option key={skillName} value={skillName}>{skillName}</option> 
                    ))}     
                     </select>
                </div>
         
                <button type='submit' className='btn btn-outline-primary'>
                    Submit
                </button>
                <Link type='submit' className='btn btn-outline-danger mx-2' to={"/home"}>
                    Cancel
                </Link>
                </div>
                </form>
            </div>
        </div>
    </div>
  )
}
