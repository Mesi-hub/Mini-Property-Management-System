import { apiSecured, getErrorMessagesContext } from "..";
import { addErrorMessage } from "../../../context/errorMessagesContext";

const getProperties = async (filterData) => {
    let result = await apiSecured()
        .get("/properties", { params: { ...filterData } })
        .catch((error) => {
            addErrorMessage(getErrorMessagesContext(), error.message);
            return error;
        });
    return result.data;
};

const getPropertyById = async (id) => {
    let result = await apiSecured()
        .get("/properties/" + id)
        .catch((error) => {
            addErrorMessage(getErrorMessagesContext(), error.message);
            return error;
        });
    return result.data;
};

const saveProperty = async (property) => {
    let result = await apiSecured()
        .post("/properties", property)
        .catch((error) => {
            addErrorMessage(getErrorMessagesContext(), error.message);
            return error;
        });
    return result.data;
};

export { getProperties, getPropertyById };
