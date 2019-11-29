package com.werwe.test;

import java.util.List;
import java.util.Optional;

public interface Command {

    /**
     * String token that signifies this command should be selected (e.g.:
     * "deposit", "withdraw")
     */
    //String key();

    /**
     * Process the rest of the command's words and do something.
     */
    Result handleInput(List<String> input);

    /**
     * A command result, which has a {@link Status} and optionally a new {@link CommandRouter} that
     * will handle subsequent commands.
     */
    final class Result {
        private final Status status;
        private final Optional<CommandRouter> nestedCommandRouter;

        private Result(Status status, Optional<CommandRouter> nestedCommandRouter) {
            this.status = status;
            this.nestedCommandRouter = nestedCommandRouter;
        }

        static Result invalid() {
            return new Result(Status.INVALID, Optional.empty());
        }

        static Result handled() {
            return new Result(Status.HANDLED, Optional.empty());
        }

        static Result inputCompleted() {
            return new Result(Status.INPUT_COMPLETED, Optional.empty());
        }

        static Result enterNestedCommandSet(CommandRouter nestedCommandRouter) {
            return new Result(Status.HANDLED, Optional.of(nestedCommandRouter));
        }

        Status status() {
            return status;
        }

        public Optional<CommandRouter> nestedCommandRouter() {
            return nestedCommandRouter;
        }

    }

    enum Status {
        /** The command or its arguments were invalid. */
        INVALID,

        /** The command handled the input and no other commands should attempt to handle it. */
        HANDLED,

        // TODO(ronshapiro): maybe call this TERMINATED? If so, maybe this should be called
        // ContinueStatus?
        /** The command handled the input and no further inputs should be submitted. */
        INPUT_COMPLETED,
        ;

    }

}
