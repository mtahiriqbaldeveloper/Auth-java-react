import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import './Signup.css';

const Signup = (props) => {
    const [validated, setValidated] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();
        const form = event.currentTarget;

        if (form.checkValidity() === false) {
            event.stopPropagation();
        } else {
            const formData = new FormData(form);
            // data variable structure =  {fullName:VALUE, email: VALUE, password: VALUE}
            const data = Object.fromEntries(formData.entries());

            fetch('YOUR_URL',
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(data)
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    console.log(data);
                    alert('Login Successful')
                })
                .catch(error => {
                    console.error('There has been a problem with your fetch operation:', error);
                });
        }

        setValidated(true);
    };

    return (
        <div className="glass-container">
            <div className="glass-form-container">
                <Form noValidate validated={validated} onSubmit={handleSubmit}>
                    <h2>SignUp</h2>
                    <Form.Group className="mb-3" controlId="fullName">
                        <Form.Label >Full Name</Form.Label>
                        <Form.Control name='fullName' required type="text" placeholder="Enter Full Name" />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="emailAddress">
                        <Form.Label >Email address</Form.Label>
                        <Form.Control name='emailAddress' required type="email" placeholder="Enter email" />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="password">
                        <Form.Label >Password</Form.Label>
                        <Form.Control name='password' required type="password" placeholder="Password" />
                    </Form.Group>

                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
            </div>
        </div >
    );
}

export default Signup;