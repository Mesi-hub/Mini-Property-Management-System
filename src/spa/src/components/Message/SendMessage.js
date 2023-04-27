import { useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { sendMessage } from "../../services/api/Messages";
import { getPropertyById } from "../../services/api/properties";

const SendMessage = () => {
  const formRef = useRef();
  const navigate = useNavigate();
  const params = useParams();
  const [property, setProperty] = useState({});
  useEffect(() => {
    getPropertyById(params.propertyId).then((data) => setProperty(data));
  }, [params.propertyId]);
  const AddHandler = (evt) => {
    evt.preventDefault();

    const form = formRef.current;

    let newMessageData = {
      message: form["message"].value,
    };
    if (property) {
      newMessageData = { ...newMessageData, property: { id: property.id } };
    }

    sendMessage(newMessageData)
      .then((res) => {
        console.log(res);
        navigate("/messages");
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return (
    <>
      {property ? (
        <div class="card">
          <div className="card-body">
            <h5 className="card-title">Inquiry on: {property.title}</h5>
          </div>
        </div>
      ) : (
        ""
      )}
      <div className="content-center  row">
        <div className="col-md-8 mx-auto mt-50 mb-50 py-5 px-5">
          <div>
            <h3>Send Message</h3>
          </div>
          <form id="newcustomerform" ref={formRef} onSubmit={AddHandler}>
            <div className="form-group">
              <label for="message" className="form-label">
                Message
              </label>
              <input
                type="text"
                className="form-control"
                id="message"
                name="message"
              />
            </div>
            <button type="submit" className="btn btn-primary">
              Send
            </button>
          </form>
        </div>
      </div>
    </>
  );
};
export default SendMessage;
