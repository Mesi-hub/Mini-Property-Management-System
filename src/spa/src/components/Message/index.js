import { useNavigate } from "react-router";

const Message = (props) => {
  const navigate = useNavigate()
  const onSendMessage = (propertyId, messageId) => {
    navigate(`/send_message/property/${propertyId}/reply/${messageId}`);
  };
  return (
    <div className="card">
      <div className="card-body">
        <h5 className="card-title">
          Sender: {props.message.sender.lastName}, {props.message.sender.firstName} [{props.message.sender.id}]<br/>
          {(props.message.recipient) ? <>Recipient: {props.message.recipient.lastName}, {props.message.recipient.firstName} [{props.message.recipient.id}]<br/></> : ""}
          {(props.message.recipropertypient) ? <>Property: {props.message.property?.title} [{props.message.property?.id}]<br/></> : ""}
          {(props.message) ? <>Reply To: {props.message?.replyTo?.message} [{props.message?.replyTo?.id}]</>:""}
        </h5>
        <h6 className="card-subtitle mb-2 text-muted">
          {props.message.date} - {props.message.time}
        </h6>
        <p className="card-text">{props.message.message}</p>
        <button className="card-link" onClick={() => {onSendMessage(props.message.property?.id, props.message?.id)}}>Reply</button>
      </div>
    </div>
  );
};
export default Message;
