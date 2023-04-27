import { useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { getMessageById, sendMessage } from "../../services/api/Messages";
import { getPropertyById } from "../../services/api/properties";

const SendMessage = () => {
  const formRef = useRef();
  const navigate = useNavigate();
  const params = useParams();
  const [property, setProperty] = useState({});
  const [message, setMessage] = useState({});
  useEffect(() => {
    if (params.propertyId) {
      getPropertyById(params.propertyId).then((data) => setProperty(data));
    }
  }, [params.propertyId]);
  useEffect(() => {
    if (params.replyToMessageId) {
      getMessageById(params.replyToMessageId).then((data) => setMessage(data));
    }
  }, [params.replyToMessageId]);

  const AddHandler = (evt) => {
    evt.preventDefault();

    const form = formRef.current;

    let newMessageData = {
      message: form["message"].value,
    };
    if (property && property.id) {
      newMessageData = { ...newMessageData, property: { id: property.id } };
    }
    if (message && message.id) {
      newMessageData = { ...newMessageData, replyTo: { id: message.id } };
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
      <div>
        <div className="album py-8 bg-light">
          <div className="container">
            <div className="row row-cols-1">
              {property ? (
                <div class="card">
                  <h5 className="card-title">
                    {message && message.recipient ? (
                      <>
                        Replying to Sender: {message?.recipient?.firstName} [
                        {message?.recipient?.id}]<br />
                      </>
                    ) : (
                      ""
                    )}
                    {message ? (
                      <>
                        Replying to Message: {message?.message} [
                        {message?.message}]
                      </>
                    ) : (
                      ""
                    )}
                  </h5>
                  <h5 className="card-title">Inquiry on: {property.title}</h5>
                </div>
              ) : (
                ""
              )}
              <div className="content-center  row">
                <div className="col-md-8 mx-auto mt-50 mb-50 py-5 px-5">
                  <div>
                    <h3>Send Message</h3>
                  </div>
                  <form
                    id="newcustomerform"
                    ref={formRef}
                    onSubmit={AddHandler}
                  >
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
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
export default SendMessage;
