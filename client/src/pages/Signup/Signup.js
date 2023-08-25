import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import './Signup.css';

const Signup = (props) => {
    const [validated, setValidated] = useState(false);

    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            fetch('YOUR_URL')
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
                        <Form.Control required type="text" placeholder="Enter Full Name" />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="emailAddress">
                        <Form.Label >Email address</Form.Label>
                        <Form.Control required type="email" placeholder="Enter email" />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="password">
                        <Form.Label >Password</Form.Label>
                        <Form.Control required type="password" placeholder="Password" />
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