"use client"

import { Button } from "./ui/button"
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "./ui/dialog";
interface ApiResponse {
  message: string;
}
import { type TodoSchema, todoSchema } from "@/lib/zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { useState } from "react";
import { mutate } from "swr";
import TodoForm from "./todo-form";

export default function CreateTodo() {
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");
    const [isDialogOpen, setDialogOpen] = useState(false);
    const form = useForm<TodoSchema>({
        resolver: zodResolver(todoSchema), //for data validation i wanna use zod
        defaultValues: {
            title: "",
            description: "",
            isCompleted: false,
        },
    });
    const onSubmit = async (data: TodoSchema) => {
      setIsSubmitting(true);
      try {
        const response = await fetch("http://localhost:8080/api/todos", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(data),
        });
        const responseData = await response.json() as ApiResponse;
        console.log("responseData", responseData)
        if (!response.ok) {
          throw new Error(responseData.message || "failed to update todo");
        }
        form.reset();
        setDialogOpen(false);
        mutate("http://localhost:8080/api/todos")
        setErrorMessage("")
      } catch (error) {
        console.error("error creating todo", error);
        const errorMessage =
          error instanceof Error
            ? error.message
            : "an unexpected error occured";
        setErrorMessage(errorMessage);
      } finally {
        setIsSubmitting(false);
      }
    };
    return (
      <Dialog open={isDialogOpen} onOpenChange={setDialogOpen}>
        <DialogTrigger asChild>
          <Button>Add Todo</Button>
        </DialogTrigger>
        <DialogContent className="sm:max-w-[425px] bg-white">
          <DialogHeader>
            <DialogTitle>Create New Todo</DialogTitle>
          </DialogHeader>
          {errorMessage && (
            <div className="text-red-500 text-sm mb-4">{errorMessage}</div>
          )}
          <TodoForm
            defaultValues={{
              title: "",
              description: "",
              isCompleted: false,
            }} onSubmit={onSubmit} submitButtonText="Create" isSubmitting={isSubmitting}
          />
        </DialogContent>
      </Dialog>
    );
}