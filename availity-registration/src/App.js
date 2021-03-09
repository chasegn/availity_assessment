import './App.css';
import React, {useState} from "react";

function App() {
  const [values, setValues] = useState({
    firstName: '',
    lastName: '',
    npiNumber: '',
    busAddress: '',
    phoneNumber: '',
    emailAddress: ''
  });

  const [submitted, setSubmitted] = useState(false);

  const [valid, setValid] = useState(false);

  const handleFirstNameInputChange = (event) => {
    event.persist();
    setValues((values) => ({
      ...values,
      firstName: event.target.value,
    }));
  }

  const handleLastNameInputChange = (event) => {
    event.persist();
    setValues((values) => ({
      ...values,
      lastName: event.target.value,
    }));
  }

  const handleNPINumberInputChange = (event) => {
    event.persist();
    setValues((values) => ({
      ...values,
      npiNumber: event.target.value,
    }));
  }

  const handleBusinessAddressInputChange = (event) => {
    event.persist();
    setValues((values) => ({
      ...values,
      busAddress: event.target.value,
    }));
  }

  const handlePhoneNumberInputChange = (event) => {
    event.persist();
    setValues((values) => ({
      ...values,
      phoneNumber: event.target.value,
    }));
  }

  const handleEmailAddressInputChange = (event) => {
    event.persist();
    setValues((values) => ({
      ...values,
      emailAddress: event.target.value,
    }));
  }

  const emailIsValid = email => {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  }

  const phoneWithSeparators = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
  const phoneWithoutSeparators = /^\d{10}$/;

  const phoneIsValid = phone => {
    return phoneWithoutSeparators.test(phone) || phoneWithSeparators.test(phone);
  }

  const handleSubmit = (e) => {
    e.preventDefault();

    if (values.firstName && values.lastName && values.npiNumber && values.busAddress
        && values.phoneNumber && phoneIsValid(values.phoneNumber)
        && values.emailAddress && emailIsValid(values.emailAddress)) {
      setValid(true);
    }

    setSubmitted(true);
  }

  const handleReset = (e) => {
    e.preventDefault();

    setSubmitted(false);
    setValid(false);

    setValues((values) => ({
      firstName: '',
      lastName: '',
      npiNumber: '',
      busAddress: '',
      phoneNumber: '',
      emailAddress: ''
    }))
  }

  return (
    <div className="form-container">
      <form class="register-form" onSubmit={handleSubmit} onReset={handleReset}>
        {submitted && valid && <div class='success-message'>Successfully registered.</div>}<br/>

        <input id="first-name" className="form-field" type="text" disabled={submitted && valid} placeholder="First Name" name="firstName" value={values.firstName} onChange={handleFirstNameInputChange}/>
        {submitted && !values.firstName && <span id="first-name-error" class="entry-error">First Name required.</span>}
        <br/>

        <input id="last-name" className="form-field" type="text" disabled={submitted && valid} placeholder="Last Name" name="lastName" value={values.lastName} onChange={handleLastNameInputChange}/>
        {submitted && !values.lastName && <span id="last-name-error" class="entry-error">Last Name required.</span>}
        <br/>

        <input id="npi-number" className="form-field" type="text" disabled={submitted && valid} placeholder="NPI Number" name="npiNumber" value={values.npiNumber} onChange={handleNPINumberInputChange}/>
        {submitted && !values.npiNumber && <span id="npi-number-error" class="entry-error">NPI Number required.</span>}
        <br/>

        <input id="business-address" className="form-field" type="text" disabled={submitted && valid} placeholder="Business Address" name="busAddress" value={values.busAddress} onChange={handleBusinessAddressInputChange}/>
        {submitted && !values.busAddress && <span id="business-address-error" class="entry-error">Business Address required.</span>}
        <br/>

        <input id="phone-number" className="form-field" type="text" disabled={submitted && valid} placeholder="Phone Number" name="phoneNumber" value={values.phoneNumber} onChange={handlePhoneNumberInputChange}/>
        {submitted && (!values.phoneNumber || !phoneIsValid(values.phoneNumber)) && <span id="phone-number-error" class="entry-error">Phone Number required.</span>}
        <br/>

        <input id="email-address" className="form-field" type="text" disabled={submitted && valid} placeholder="Email Address" name="emailAddress" value={values.emailAddress} onChange={handleEmailAddressInputChange}/>
        {submitted && (!values.emailAddress || !emailIsValid(values.emailAddress)) && <span id="email-address-error" class="entry-error">Email Address required.</span>}
        <br/>

        <button class="form-field" type="submit">Register</button>
        <button class="form-field" type="reset">Reset</button>
      </form>
    </div>
  );
}

export default App;
