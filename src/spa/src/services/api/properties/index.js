import { apiSecured } from "..";

const getProperties = async () => {
    let result = await apiSecured().get("/properties");
    return result.data;
};

const getPropertyById = async (id) => {
    let result = await apiSecured().get("/properties/" + id);
    return result.data;
};

const saveProperty = async (property) => {
    let result = await apiSecured().post("/properties", property);
    return result.data;
};

export { getProperties, getPropertyById };
